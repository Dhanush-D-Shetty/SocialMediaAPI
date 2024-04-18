package com.shetty.socialmedia.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shetty.socialmedia.entittes.Message;

public interface MessageRepostery  extends JpaRepository<Message, Integer>{

	public List<Message> findByChatId(Integer chatId);
}
