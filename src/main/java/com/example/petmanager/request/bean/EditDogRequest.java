package com.example.petmanager.request.bean;

 

import org.springframework.validation.annotation.Validated;

import com.example.petmanager.util.ValidationConstants;  
import com.example.petmanager.response.bean.EPSerializer;

@Validated 
public class EditDogRequest implements EPSerializer {
	
 
	private static final long serialVersionUID = 5545058097032630977L;
	 
	 private long id;
	 
	 private EditDogDetails details;
	 
	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EditDogDetails getDetails() {
		return details;
	}

	public void setDetails(EditDogDetails details) {
		this.details = details;
	}
	 
	 
	 


}
