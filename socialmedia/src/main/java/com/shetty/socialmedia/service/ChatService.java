package com.shetty.socialmedia.service;

import java.util.List;

import com.shetty.socialmedia.entittes.Chat;
import com.shetty.socialmedia.entittes.User;

public interface ChatService {
	
	public Chat createChat(User logInUser,User user2);	
	public Chat findChatById(Integer chatId) throws Exception;
	public List<Chat> findUsersChat(Integer userId);

}
