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
public class EditDogDetails implements EPSerializer {
	
 
	private static final long serialVersionUID = 5545058097032630977L;
	
	@Size(min = 3, max = 100)
	@NotBlank
	@Pattern(regexp=ValidationConstants.Regex.ALPHANUMERIC_EN, message ="must be alphanumeric with spaces") 
	private String name;
	
	@Size(max = 250)
	private String breed;
	
	
	@Size(max = 100)
	private String height;
	
	@Size(max = 100)
	private String weight;
	
	 
	private int age;
	
	@Size(max = 200)
	private String color;

	@Min(0)
	public int getAge() {
		// TODO Auto-generated method stub
		return age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setAge(int age) {
		this.age = age;
	} 


}
