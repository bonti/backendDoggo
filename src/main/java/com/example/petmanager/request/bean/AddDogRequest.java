package com.example.petmanager.request.bean;

import java.util.List;
 
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.example.petmanager.util.ValidationConstants;  
import com.example.petmanager.response.bean.EPSerializer;

@Validated 
public class AddDogRequest implements EPSerializer {
	
 
	private static final long serialVersionUID = 5545058097032630977L;
	 
	private AddDogDetails details;

	public AddDogDetails getDetails() {
		return details;
	}

	public void setDetails(AddDogDetails details) {
		this.details = details;
	}
	
	

}
