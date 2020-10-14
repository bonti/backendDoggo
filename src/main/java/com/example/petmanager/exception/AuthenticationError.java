package com.example.petmanager.exception;

import java.util.Date;

import org.springframework.security.core.AuthenticationException;

import com.example.petmanager.response.bean.UserInfo;


public class AuthenticationError extends AuthenticationException{
	
	private static final long serialVersionUID = 1234455567L;

	private Date timestamp;
	
	private Integer httpSubStatus;
	
	private String message;
	
	private String path;
	
	private UserInfo userInfo;

	public AuthenticationError(String message){
	    super(message);
	    this.message = message;
	}
	
	public AuthenticationError(String message, Integer httpSubStus){
	    super(message);
	    this.message = message;
	    this.httpSubStatus = httpSubStus;
	}
	
	public AuthenticationError(String message, Integer httpSubStus, UserInfo userInfo) {
		super(message);
	    this.message = message;
	    this.httpSubStatus = httpSubStus;
	    this.userInfo = userInfo;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getHttpSubStatus() {
		return httpSubStatus;
	}

	public void setHttpSubStatus(Integer httpSubStatus) {
		this.httpSubStatus = httpSubStatus;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
