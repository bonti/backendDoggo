package com.example.petmanager.entity; 
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.petmanager.entity.User;
 

@Entity
public class Dog extends BaseEntitiy {
 
 
	private static final long serialVersionUID = 7989408837310817051L;
private String name;
  private String height;
  private String weight;
  private String breed;
  private int age;
  private String color;
  
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Dog()  {}

  public Dog(String name, String height,String weight, String breed, int age, String color) {
    this.setName(name);
    this.height = height;
    this.weight= weight;
    this.breed=breed;
    this.age= age;
  }
 

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
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

public String getBreed() {
	return breed;
}

public void setBreed(String breed) {
	this.breed = breed;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}


 
}