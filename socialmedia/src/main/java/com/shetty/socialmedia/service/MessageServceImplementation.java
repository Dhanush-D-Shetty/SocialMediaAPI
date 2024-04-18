package com.shetty.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;

import com.shetty.socialmedia.entittes.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shetty.socialmedia.entittes.Chat;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.repositery.ChatRepositery;
import com.shetty.socialmedia.repositery.MessageRepostery;

@Service
public class MessageServceImplementation implements MessageService{

	
	@Autowired
	MessageRepostery messageRepostery;
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	ChatRepositery chatRepositery;

	@Override
	public Message createMessage(User logInuser, Integer chatId,Message req) throws Exception {

	Chat chat=	chatService.findChatById(chatId);
		
		Message msg=new Message();
		msg.setChat(chat);
		msg.setContent(req.getContent());
		msg.setImage(req.getImage());
		msg.setUsers(logInuser);
		msg.setTimestamp(LocalDateTime.now());
		
		Message savedMessage=messageRepostery.save(msg);
		
//		saveing the meassges in chat
		chat.getMessages().add(savedMessage);
		chatRepositery.save(chat);
		return savedMessage;
	}
	
	

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {

		Chat chat=	chatService.findChatById(chatId);
		return messageRepostery.findByChatId(chatId);
	}






}
