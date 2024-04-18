package com.shetty.socialmedia.response;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shetty.socialmedia.entittes.Comment;

public interface CommentRepositery extends JpaRepository<Comment, Integer> {

}
