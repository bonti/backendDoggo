package com.example.petmanager.service;
 

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.petmanager.request.bean.AddDogRequest;
import com.example.petmanager.request.bean.EditDogRequest;
import com.example.petmanager.response.bean.DogDetailResponse;
import com.example.petmanager.response.bean.DogListResponse;
  
@Service
public interface DogService {
	 
	public DogListResponse getDogsByUser(long userId);
	public DogDetailResponse getDogDetailById(long id);
	public DogDetailResponse editDogDetail(EditDogRequest editDogRequest, long id);	
	public DogDetailResponse addDogDetail(AddDogRequest addDogRequest, long userId);
}
