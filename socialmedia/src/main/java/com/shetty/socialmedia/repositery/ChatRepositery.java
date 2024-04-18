package com.shetty.socialmedia.repositery;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shetty.socialmedia.entittes.Chat;
import com.shetty.socialmedia.entittes.User;

public interface ChatRepositery extends JpaRepository<Chat, Integer>{

	public List<Chat> findByUsersId(Integer userId);
	
	@Query("select c from Chat c where :user Member of c.users And :logInUser Member of c.users")
	public Chat findChatByUserId(@Param("user") User user,@Param("logInUser") User logInUser);
}
