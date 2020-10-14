package com.example.petmanager.response.bean;
 
import com.fasterxml.jackson.annotation.JsonProperty;
 
 
public class BaseResponseBean implements EPSerializer {
	
	private static final long serialVersionUID = 1L;

	
	@JsonProperty("returnMessage")
	protected String returnMessage;

 
	

 


}
