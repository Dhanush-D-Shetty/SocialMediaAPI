package com.shetty.socialmedia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shetty.socialmedia.config.JwtProvider;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.repositery.UserReposiitery;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserReposiitery userReposiitery;

	@Override // create User
	public User registerUser(User user) {

		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setId(user.getId());
		newUser.setGender(user.getGender());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());

		User savedUser = userReposiitery.save(newUser);
		return savedUser;
	}

	@Override
	public User getUsersById(Integer userId) throws Exception {

		Optional<User> user = userReposiitery.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}
		throw new Exception("user not find" + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userReposiitery.findByEmail(email);
		return user;
	}

//	  user1(logInUser) follows user2 i.e user1 following increases and user2 follower increases
	@Override
	public User followUser(Integer logInUserId, Integer userId2) throws Exception {

		User logInUser = getUsersById(logInUserId); // above getUserBYid method is used;
		User user2 = getUsersById(userId2);

		user2.getFollowers().add(logInUser.getId());
		logInUser.getFollowing().add(user2.getId());

		userReposiitery.save(logInUser);
		userReposiitery.save(user2);
		return logInUser;
	}

	@Override // frontend user data(User user)
	public User updateUser(User user, Integer userId) throws Exception {
		Optional<User> user1 = userReposiitery.findById(userId); // backend user data

		if (user1.isEmpty()) {
			throw new Exception("user not exist" + userId);
		}

		User oldUser = user1.get();
		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		if (user.getPassword() != null) {
			oldUser.setPassword(user.getPassword());
		}

		User updatedUser = userReposiitery.save(oldUser);
		return oldUser;

	}

	@Override
	public List<User> searchUser(String query) {
		return userReposiitery.searchUser(query);
	}

	public List<User> getAllUsers() {
		List<User>	allUser =userReposiitery.findAll();
		return allUser;
	}
	
	
	
// fetching profile of the sigined user
	@Override
	public User findUserByToken(String jwtToken) {
		
		String email=JwtProvider.getEmailFromToken(jwtToken); // get email from token		
		User user=userReposiitery.findByEmail(email); // getting user using above email
		return user;
	}
}
