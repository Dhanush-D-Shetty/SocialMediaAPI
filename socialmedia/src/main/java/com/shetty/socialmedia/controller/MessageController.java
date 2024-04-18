package com.shetty.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shetty.socialmedia.entittes.Chat;
import com.shetty.socialmedia.entittes.Message;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.request.CreateChatRequest;
import com.shetty.socialmedia.service.ChatService;
import com.shetty.socialmedia.service.MessageService;
import com.shetty.socialmedia.service.UserService;

@RestController
public class MessageController {
	@Autowired
	MessageService messageService;

	@Autowired
	UserService userService;

	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestHeader("Authorization") String jwtToken,@PathVariable Integer chatId,@RequestBody Message msg) throws Exception {

		User logInUser = userService.findUserByToken(jwtToken);
		Message message = messageService.createMessage(logInUser, chatId, msg);
		return message;
	}
	
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> createMessage(@RequestHeader("Authorization") String jwtToken,@PathVariable Integer chatId) throws Exception {

		User logInUser = userService.findUserByToken(jwtToken);
		List<Message> messages = messageService.findChatsMessages(chatId);
		return messages;
	}

}
