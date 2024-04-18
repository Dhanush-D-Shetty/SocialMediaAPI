package com.shetty.socialmedia.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shetty.socialmedia.entittes.Reels;

public interface ReelsRepostery extends JpaRepository<Reels, Integer>{

	public List<Reels> findByUserId(Integer userId);
}
