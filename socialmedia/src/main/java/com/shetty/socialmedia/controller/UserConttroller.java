package com.shetty.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.service.UserService;
import com.shetty.socialmedia.service.UserServiceImplementation;

@RestController
public class UserConttroller {

	@Autowired
	UserService userService;

	

	@GetMapping("/api/users/getusers")
	public List<User> getAllUsers() {
		List<User>	allUser =userService.getAllUsers();
		return allUser;
	}

	@GetMapping("/api/users/{userId}")
	public User getUsersById(@PathVariable("userId") Integer id) throws Exception {
		User user = userService.getUsersById(id);
		return user;
	}

//	 loggedIn user can update only his account
//	@PutMapping("/api/updateuser/{userId}")  
	@PutMapping("/api/updateuser")
	public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwtToken) throws Exception {	
		
		User loggedInUser = userService.findUserByToken(jwtToken);  // fetch user by token
		User updatedUser = userService.updateUser(user, loggedInUser.getId());
		return updatedUser;
	}

	
//	@PutMapping("/api/users/follow/{userId1}/{userId2}")
	@PutMapping("/api/users/follow/{userId2}")
	public User followUser(@RequestHeader("Authorization") String jwtToken,@PathVariable Integer userId2) throws Exception {
	
		User logInUser = userService.findUserByToken(jwtToken);  // fetch user by token
		User user=userService.followUser(logInUser.getId(), userId2);
		return user;
	}

	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users=userService.searchUser(query);
		return users;
	}
	
	
//   loggedIn user fetching his profile
	@GetMapping("/api/users/profile")
	public User getUserProfileByToken(@RequestHeader("Authorization") String jwtToken){
		
		User user=userService.findUserByToken(jwtToken);
		user.setPassword(null);
		return user;
	}
}
