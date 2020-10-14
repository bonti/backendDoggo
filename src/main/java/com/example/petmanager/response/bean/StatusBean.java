package com.example.petmanager.response.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.petmanager.exception.ApiError;
import com.example.petmanager.exception.EnterpriseExceptionHandler;
import com.fasterxml.jackson.annotation.JsonProperty;



public class StatusBean implements EPSerializer {


	private static final long serialVersionUID = -7007390830805887383L;

	
	private static final String DEFAULT_ERROR_KEY = "error";
	
	private enum StatusEnum {
		
		Success (HttpStatus.OK),
		ValidationFailure (HttpStatus.BAD_REQUEST),
		ServerFailure (HttpStatus.INTERNAL_SERVER_ERROR),
		NotFoundFailure (HttpStatus.NOT_FOUND),
		HttpMethodNotSupportedFailure (HttpStatus.METHOD_NOT_ALLOWED),
		AuthenticationFailure (HttpStatus.UNAUTHORIZED);
		
		private HttpStatus httpStatus;
		private StatusEnum (HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
		}
	}

	
	@JsonProperty("httpStatusCode")
	private int 		httpStatusCode;
	
 
	
	@JsonProperty("statusDescription")
	private StatusEnum  statusDescription;
	
	 
	@JsonProperty("errorMessage")
	private List<ApiError>	listOfErrorMessages = new ArrayList<>();


	private StatusBean (HttpStatus httpStatus,   String key, String errorMessage) {
		this(httpStatus,  
				StringUtils.isEmpty(errorMessage)? null: Arrays.asList(new ApiError(key, errorMessage, null)));
	}
	
	private StatusBean (HttpStatus httpStatusCode, List<ApiError> errorsList) {
		
		//set List of Errors
		if (errorsList != null) {
			this.listOfErrorMessages.addAll(errorsList);
		} 

		//set Http Status
		this.httpStatusCode = httpStatusCode.value(); 
	 		
	}

	
	public static StatusBean getSuccess (HttpStatus httpSubStatusCode) {
		return new StatusBean(HttpStatus.OK,   null, null);
	}

	 
	public static StatusBean getFailure (HttpStatus httpStatus,  String error) {
		return new StatusBean(httpStatus, DEFAULT_ERROR_KEY, error);
	}
	
	public static StatusBean getSuccess () {
		return new StatusBean(HttpStatus.OK,  null);
	}
	
	public static StatusBean getValidationFailure (List<ApiError>	listOfErrorMessages ) {
		return new StatusBean(HttpStatus.BAD_REQUEST,  listOfErrorMessages);
	}
	
	  

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	 

	public StatusEnum getStatusDescription() {
		return statusDescription;
	}

	public List<ApiError> getErrorMessage() {
		return listOfErrorMessages;
	}


}