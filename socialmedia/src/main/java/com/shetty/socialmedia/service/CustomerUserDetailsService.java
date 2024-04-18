package com.shetty.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.repositery.UserReposiitery;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	UserReposiitery userReposiitery;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userReposiitery.findByEmail(username);  // email

		if (user == null) {
			throw new UsernameNotFoundException("user not found with email" + username);
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}
