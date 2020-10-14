package com.example.petmanager.exception;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8259411894839745642L;
	
	
	String field;
	String message;
	String value;

	public ValidationException(String field, String value, String message) {
		super();
		this.field = field;
		this.message = message;
		this.value = value;
	}
	
	
	public String getField() {
		return field;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	public String getValue() {
		return value;
	}
	
}
