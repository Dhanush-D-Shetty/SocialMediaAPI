package com.shetty.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shetty.socialmedia.entittes.Chat;
import com.shetty.socialmedia.entittes.Reels;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.request.CreateChatRequest;
import com.shetty.socialmedia.service.ChatService;
import com.shetty.socialmedia.service.UserService;

@RestController
public class ChatController {

	@Autowired
	ChatService chatService;

	@Autowired
	UserService userService;

	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwtToken,@RequestBody CreateChatRequest req) throws Exception {

		User logInUser = userService.findUserByToken(jwtToken);
		User user2 = userService.getUsersById(req.getUserId());

		Chat chat = chatService.createChat(logInUser, user2);
		return chat;
	}

	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwtToken) {
		
		User logInUser = userService.findUserByToken(jwtToken);
		List<Chat> chats = chatService.findUsersChat(logInUser.getId());
		return chats;
	}

}
