package com.shetty.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shetty.socialmedia.entittes.Comment;
import com.shetty.socialmedia.entittes.Reels;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.service.CommentService;
import com.shetty.socialmedia.service.ReelsService;
import com.shetty.socialmedia.service.UserService;

@RestController
public class ReelsController {
	
	@Autowired
	ReelsService reelsService;

	@Autowired
	UserService userService;

	@PostMapping("/api/reels")
	public Reels createReels( @RequestBody Reels reel,@RequestHeader("Authorization") String jwtToken) throws Exception {
		User logInUser = userService.findUserByToken(jwtToken);
		Reels newReel = reelsService.createReels(reel, logInUser);
		return newReel;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels(){
		 List<Reels> reels = reelsService.findAllReels();
		return reels;
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUsersReels(@PathVariable("userId") Integer userId) throws Exception{
		 List<Reels> reels = reelsService.findUserReels(userId);
		return reels;
	}


}
