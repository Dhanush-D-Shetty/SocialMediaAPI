package com.shetty.socialmedia.service;

import java.util.List;

import com.shetty.socialmedia.entittes.Reels;
import com.shetty.socialmedia.entittes.User;

public interface ReelsService {

	public Reels createReels(Reels reel, User user);
	public List<Reels> findAllReels();
	public List<Reels> findUserReels(Integer userId) throws Exception;
}
