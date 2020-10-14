package com.example.petmanager.response.bean;

public class DogDetail extends BaseResponseBean {
 
private static final long serialVersionUID = 3282627033537923544L;

  private String name;
  private String breed;
  private String height;
  private String weight;
  private int age; 
  private String color;
  

  protected DogDetail()  {}

  public DogDetail(String name, String height,String weight, String breed, int age, String color) {
    this.setName(name);
    this.name = name;
    this.breed= breed;
    this.height = height;
    this.weight = weight;
    this.age = age;
    this.color = color;
  
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
}