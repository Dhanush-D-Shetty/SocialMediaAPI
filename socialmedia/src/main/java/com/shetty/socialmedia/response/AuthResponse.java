package com.shetty.socialmedia.response;

public class AuthResponse {
	
	private String token;
	private String message;
	

	public AuthResponse() {
	
	}
	
	
	public AuthResponse(String token,String message) {
		super();
		this.token = token;
		this.message = message;	
	}
	
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
