package com.shetty.socialmedia.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shetty.socialmedia.entittes.Comment;
import com.shetty.socialmedia.entittes.Post;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.repositery.PostRepositery;
import com.shetty.socialmedia.response.CommentRepositery;


@Service
public class CommentServiceImplemtation implements CommentService {
	
	
	@Autowired
	CommentRepositery commentRepositery;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	PostRepositery postRepositery;
	

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User user=userService.getUsersById(userId);
		Post post=	postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		Comment savedComment=commentRepositery.save(comment);
		
		post.getComments().add(savedComment);
		postRepositery.save(post);
		
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {

		Optional<Comment>  comment = commentRepositery.findById(commentId);

		if (comment.isEmpty()) {
			throw new Exception("comment not exist");
		}

		return comment.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		
		Comment comment = findCommentById(commentId);
		User user = userService.getUsersById(userId);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);          // save
		}else {
			comment.getLiked().remove(user);	  // unsave
		}
		
		return commentRepositery.save(comment);
	}

}
