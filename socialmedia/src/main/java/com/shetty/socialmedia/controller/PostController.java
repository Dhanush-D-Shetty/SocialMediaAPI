package com.shetty.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shetty.socialmedia.entittes.Post;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.response.ApiResponse;
import com.shetty.socialmedia.service.PostService;
import com.shetty.socialmedia.service.UserService;

@RestController
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;

//	user can create post only in his account 
//	he cannot create post in another account by passing userId
//	@PostMapping("/posts/user/{userId}")
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwtToken,@RequestBody Post post) throws Exception {

		User logInUser = userService.findUserByToken(jwtToken);
		Post newPost = postService.createNewPost(post, logInUser.getId());
		return new ResponseEntity<>(newPost, HttpStatus.ACCEPTED);
	}
 

	
	
//	@DeleteMapping("/posts/{postId}/user/{userId}")
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwtToken,@PathVariable Integer postId)
			throws Exception {
		
		User logInUser = userService.findUserByToken(jwtToken);
		String msg = postService.deletePost(postId, logInUser.getId());
		ApiResponse res = new ApiResponse(msg, true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}

	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {

		Post post = postService.findPostById(postId);
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}

	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findPostByUser(@PathVariable Integer userId) {

		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost() {

		List<Post> posts = postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	
//	login user saves the post 
//	@PutMapping("/posts/save/{postId}/user/{userId}")
	@PutMapping("/posts/save/{postId}")
	public ResponseEntity<Post> savedPost(@RequestHeader("Authorization") String jwtToken ,@PathVariable Integer postId) throws Exception {
	
		User logInUser = userService.findUserByToken(jwtToken);

		Post post = postService.savedPost(postId, logInUser.getId());
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
//	login user can like the photo
//	@PutMapping("/posts/like/{postId}/user/{userId}")
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePost(@PathVariable Integer postId,@RequestHeader("Authorization") String jwtToken) throws Exception {
		User logInUser = userService.findUserByToken(jwtToken);
		Post post = postService.likePost(postId, logInUser.getId());
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}

}
