package com.shetty.socialmedia.response;

public class ApiResponse {
	
	private String msg;
	private boolean status;
	
	public ApiResponse() {
		
	}
	
	
	public ApiResponse(String msg, boolean status) {
		super();
		this.msg = msg;
		this.status = status;
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
