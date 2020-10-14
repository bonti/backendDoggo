package com.example.petmanager.response.bean;

public class DogList extends BaseResponseBean {
 
private static final long serialVersionUID = 3282627033537923544L;

  private String name;
  private String breed;  
  private long id;

  public DogList()  {}

  public DogList(String name,   String breed,  long id) {
    this.setName(name);
    this.name = name;
    this.breed= breed; 
    this.id = id;
  }
  
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
 public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getBreed() {
	return breed;
}

public void setBreed(String breed) {
	this.breed = breed;
}
 

 
}