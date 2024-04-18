package com.shetty.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shetty.socialmedia.entittes.Post;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.repositery.PostRepositery;
import com.shetty.socialmedia.repositery.UserReposiitery;

@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	PostRepositery postRepositery;

	@Autowired
	UserService userService;
	

	@Autowired
	UserReposiitery userRepositery;


	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {

		User user = userService.getUsersById(userId);

		Post newPost = new Post();
		newPost.setImageUrl(post.getImageUrl());
		newPost.setVedioUrl(post.getVedioUrl());
		newPost.setCaption(post.getCaption());
		newPost.setCreatedAt( LocalDateTime.now());
		newPost.setUser(user);

		return postRepositery.save(newPost);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> opt = postRepositery.findById(postId);
		if (opt.isEmpty()) {
			throw new Exception("post not found with id" + postId);
		}

		return opt.get();
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.getUsersById(userId);
//      user can delete only his post not others post
		if (post.getUser().getId() != user.getId()) {
			throw new Exception("you cannot delete others post");
		}
		postRepositery.delete(post);

		return "post deletd succesfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		return postRepositery.findPostByUserId(userId);
	}

	@Override
	public List<Post> findAllPost() {
		return postRepositery.findAll();
	}

	

	
	@Override
	public Post savedPost(Integer postId, Integer userId)throws Exception {
	
		Post post = findPostById(postId);
		User user = userService.getUsersById(userId);
		
		// user saveing and unsaveing the post
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);   // unsave
		}else {
			user.getSavedPost().add(post);	  // save
		}
		
		userRepositery.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId)throws Exception {
		
		Post post = findPostById(postId);
		User user = userService.getUsersById(userId);
		
		// user like and dislike the post
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);   // dislike
		}else {
			post.getLiked().add(user);	  // like
		}
		
		return postRepositery.save(post);
	}

}
