package com.example.petmanager.exception;

public class ApiError {

	String key;
	String message;
	String value;

	public ApiError(String key, String message, String value) {
		super();
		this.key = key;
		this.message = message;
		this.value = value;
	}
	
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
