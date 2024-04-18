package com.shetty.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shetty.socialmedia.entittes.Reels;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.repositery.ReelsRepostery;

@Service
public class ReelsServiceImplementation implements ReelsService{

	@Autowired
	ReelsRepostery reelsRepostery;
	
	@Autowired
	UserService userService;
	
	@Override
	public Reels createReels(Reels reel, User user) {
		
		Reels createReel=new Reels();
		createReel.setTitle(reel.getTitle());
		createReel.setVideo(reel.getVideo());		
		createReel.setUser(user);

		return reelsRepostery.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		return reelsRepostery.findAll();
	}

	@Override
	public List<Reels> findUserReels(Integer userId) throws Exception {
		
		userService.getUsersById(userId); // checking whether user exists		
		return reelsRepostery.findByUserId(userId);
	}

}
