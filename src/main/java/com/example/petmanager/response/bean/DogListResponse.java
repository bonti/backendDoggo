package com.example.petmanager.response.bean;

import java.util.List;

public class DogListResponse extends BaseResponseBean {
 
private static final long serialVersionUID = 3282627033537923544L;


  public DogListResponse()  {}

   private List<DogList> doggos ; 

	public List<DogList> getDoggos() {
		return doggos;
	}
	
	public void setDoggos(List<DogList> response) {
		this.doggos = response;
	}
	    
}