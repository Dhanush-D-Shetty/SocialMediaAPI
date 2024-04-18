package com.shetty.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shetty.socialmedia.config.JwtProvider;
import com.shetty.socialmedia.entittes.User;
import com.shetty.socialmedia.repositery.UserReposiitery;
import com.shetty.socialmedia.request.LoginRequest;
import com.shetty.socialmedia.response.AuthResponse;
import com.shetty.socialmedia.service.CustomerUserDetailsService;
import com.shetty.socialmedia.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserReposiitery userReposiitery;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	CustomerUserDetailsService customerUserDetailsService;

//	@PostMapping("/users")
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {

		User isExist = userReposiitery.findByEmail(user.getEmail());

		if (isExist != null) {
			throw new Exception("user with this email id exist please use another email to register");
		}

		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setGender(user.getGender());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userReposiitery.save(newUser);

		Authentication authencation = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());
		String token = JwtProvider.generateToken(authencation);
		AuthResponse res = new AuthResponse(token, "register succesfull");
		
		return res;
	}

	@PostMapping("/signin")
	public AuthResponse signIn(@RequestBody LoginRequest loginrequest) {

		Authentication authencation = authenticate(loginrequest.getEmail(), loginrequest.getPassword());
		String token = JwtProvider.generateToken(authencation);
		AuthResponse res = new AuthResponse(token, "log-in succesfull");
		
		return res;
	}

	public Authentication authenticate(String email, String password) {

		UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

		if (userDetails == null) {
			throw new BadCredentialsException("invalid username"); // if user not loaded
		}                         // frontend pwd ,  backend pwd
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not matching");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}

}
