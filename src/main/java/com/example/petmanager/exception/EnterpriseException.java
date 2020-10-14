package com.example.petmanager.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class EnterpriseException extends RuntimeException{

private static final long serialVersionUID = 7718828512143293558L;
	
	private String error;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private Date timestamp;
	
	private HttpStatus status;
	
	private String path;

	public EnterpriseException(String message, Throwable cause, String error) {
		super(message, cause);
		this.error = error;
		this.timestamp = new Date();
	}

	public EnterpriseException(String message, String error) {
		super(message);
		this.error = error;
		this.timestamp = new Date();
	}
	
	public EnterpriseException(String message, String error, String path) {
		super(message);
		this.error = error;
		this.timestamp = new Date();
		this.path = path;
	}

	public EnterpriseException(Throwable cause, String error) {
		super(cause);
		this.error = error;
		this.timestamp = new Date();
	}	
	
	public EnterpriseException(String message, HttpStatus status) {
		super(message);
		this.status = status;
		this.timestamp = new Date();
	}
}
