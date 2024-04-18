package com.shetty.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shetty.socialmedia.entittes.Chat;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.repositery.ChatRepositery;


@Service
public class ChatServiceImplementation implements ChatService {

	@Autowired
	ChatRepositery chatRepositery;

	@Override
	public Chat createChat(User logInUser, User user2) {
		Chat isExist = chatRepositery.findChatByUserId(user2, logInUser);
		if (isExist != null) {
			return isExist;
		}

		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(logInUser);
		chat.setTimestamp(LocalDateTime.now());
		
		return chatRepositery.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> opt=chatRepositery.findById(chatId);
		if(opt.isEmpty()) {
			throw new Exception("chat not found with id = "+chatId);
		}
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		
		return chatRepositery.findByUsersId(userId);
	}

}
