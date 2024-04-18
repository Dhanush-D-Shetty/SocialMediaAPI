package com.shetty.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shetty.socialmedia.entittes.Comment;
import com.shetty.socialmedia.entittes.Post;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.service.CommentService;
import com.shetty.socialmedia.service.UserService;

@RestController
public class CommentContoller {

	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;

	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment(@RequestHeader("Authorization") String jwtToken, @RequestBody Comment comment,
			@PathVariable int postId) throws Exception {

		User logInUser = userService.findUserByToken(jwtToken);
		Comment newcomment = commentService.createComment(comment, postId, logInUser.getId());
		return newcomment;
	}
	
//	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwtToken,
			@PathVariable int commentId) throws Exception {

		User logInUser = userService.findUserByToken(jwtToken);
		Comment likedcomment = commentService.likeComment(commentId, logInUser.getId());
		return likedcomment;
	}

}
