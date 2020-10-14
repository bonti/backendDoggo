package com.example.petmanager.response.bean;

public class DogDetailResponse extends BaseResponseBean {
 
private static final long serialVersionUID = 3282627033537923544L;

  private DogDetail details;
  private long id;

  protected DogDetailResponse()  {}

  public DogDetailResponse(String name, String height,String weight, String breed, int age, String color, long id) {
    
    this.setDetails(new DogDetail(name,height,weight,breed, age, color));
    this.id = id;
  } 
  
 public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public DogDetail getDetails() {
	return details;
}

public void setDetails(DogDetail details) {
	this.details = details;
}
 
}