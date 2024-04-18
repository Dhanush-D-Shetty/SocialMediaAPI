package com.shetty.socialmedia.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

	private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

//	 method returns token in String format
	public static String generateToken(Authentication auth) {

		String jwt = Jwts.builder().setIssuer("DhanushShetty").setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + 86400000))// sets expiration time for 24hours (it will expire after 24 hour)
				.claim("email", auth.getName()).signWith(key)
				.compact(); 
//		  token is generated and stored in jwt
		return jwt;
	}

//	to get email from token
	public static String getEmailFromToken(String jwt) {
		
//   Bearer token (jwt token will be in this form,to remove "Bearer" from token substring method is used)
		jwt=jwt.substring(7);
		
		Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String email=String.valueOf(claims.get("email"));
		return email;
	}
}
