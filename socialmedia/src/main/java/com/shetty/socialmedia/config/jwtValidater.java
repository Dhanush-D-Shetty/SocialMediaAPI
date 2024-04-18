package com.shetty.socialmedia.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidater extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String jwt=request.getHeader(JwtConstant.JWT_HEADER);// to recive token from header
	
		if(jwt!=null) {
			try {
				String email=JwtProvider.getEmailFromToken(jwt);
				List<GrantedAuthority> authorities=new ArrayList<>();
				Authentication authentication=new UsernamePasswordAuthenticationToken(email,null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				
			} catch (Exception e) {
				throw new BadCredentialsException("inavlid token.....");
			}
		}
		
		filterChain.doFilter(request, response);
	}



}
