package com.shetty.socialmedia.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shetty.socialmedia.entittes.Post;

public interface PostRepositery extends JpaRepository<Post,Integer> {

	@Query("select p from Post p where p.user.id=:userId")
	List<Post> findPostByUserId(Integer userId);
}
