package com.shetty.socialmedia.service;

import java.util.List;

import com.shetty.socialmedia.entittes.Message;
import com.shetty.socialmedia.entittes.User;

public interface MessageService {

	public Message createMessage(User logInuser, Integer chatId,Message req) throws Exception ;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
