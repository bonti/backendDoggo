package com.example.petmanager.service.implementation; 
   
import com.example.petmanager.service.DogService;
 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.petmanager.exception.EnterpriseException; 
import com.example.petmanager.repository.UserRepository;
import com.example.petmanager.request.bean.AddDogRequest;
import com.example.petmanager.request.bean.EditDogDetails;
import com.example.petmanager.request.bean.EditDogRequest;
import com.example.petmanager.entity.Dog; 
import com.example.petmanager.entity.User;
import com.example.petmanager.repository.DogRepository;
import com.example.petmanager.response.bean.BaseResponseBean;
import com.example.petmanager.response.bean.DogDetailResponse;
import com.example.petmanager.response.bean.DogList;
import com.example.petmanager.response.bean.DogListResponse;
import com.example.petmanager.response.bean.ResponseWrapper;
import com.example.petmanager.security.JwtAuthenticationEntryPoint;

 
@Validated
@Service
public class DogServiceImpl extends BaseService implements DogService{ 

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Autowired
	DogRepository dogRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public DogListResponse getDogsByUser(long userId) {
		List<Dog> dogsForUser = dogRepository.findByUserId(userId);
		List<DogList> response = new ArrayList<DogList>();
		if(dogsForUser.isEmpty())
		{
			throw new EnterpriseException("The user has no dogs", HttpStatus.NOT_FOUND);
		}
		for (Dog dog : dogsForUser) 
		{ 
		     DogList  dogBrief = new DogList (); 
		     dogBrief.setBreed(dog.getBreed());
		     dogBrief.setId(dog.getId());
		     dogBrief.setName(dog.getName());
		     response.add(dogBrief); 
		}
		
		
		DogListResponse dlresponse = new DogListResponse();
		dlresponse.setDoggos(response);
		return dlresponse;
	}

	@Override
	public DogDetailResponse getDogDetailById(long id) {
		Optional<Dog> dog = dogRepository.findById(id);
		if(dog.isEmpty())  
		{
			throw new EnterpriseException("No Dog found", HttpStatus.NOT_FOUND);
		}
		Dog dogDetail = dog.get();
		DogDetailResponse dogResponse = new DogDetailResponse(dogDetail.getName(), dogDetail.getHeight(), dogDetail.getWeight(), dogDetail.getBreed(), dogDetail.getAge(), dogDetail.getColor(), dogDetail.getId());
		return dogResponse; 
	}


	
	@Override
	public DogDetailResponse editDogDetail(EditDogRequest request, long id) {

		
		//Should not need to do this! The Constraint Validation is not getting triggered for some reason;
		validateEditRequest(request);
		
		 Optional<Dog> dog = dogRepository.findById(id);
		  if(dog.isEmpty()) {  
			 logger.error("Doggo does not exist"+id);
			 throw new EnterpriseException("Dog does not exist as per request", HttpStatus.BAD_REQUEST);  
		 } 
		 
		 try {
			 Dog dbDog = dog.get(); 
				 if(request.getDetails().getAge()>0 ) dbDog.setAge(request.getDetails().getAge());
				 if(request.getDetails().getBreed()!=null ) dbDog.setBreed(request.getDetails().getBreed());
				 if(request.getDetails().getColor()!=null )dbDog.setColor(request.getDetails().getColor());
				 if(request.getDetails().getHeight()!=null )dbDog.setHeight(request.getDetails().getHeight());
				 if(request.getDetails().getWeight()!=null )dbDog.setWeight(request.getDetails().getWeight());
				 if(request.getDetails().getName()!=null )dbDog.setName(request.getDetails().getName());
			 Dog savedDog=dogRepository.save(dbDog);
			 DogDetailResponse savedDogResponse = new DogDetailResponse(savedDog.getName(), savedDog.getHeight(), savedDog.getWeight(), savedDog.getBreed(), savedDog.getAge(), savedDog.getColor(), savedDog.getId());
			 return savedDogResponse; 
		 }
		 catch(Exception ex){
			 logger.error("An exception occurred editing Dog Info "+ex.getMessage());
			throw new EnterpriseException("We were not able to save the dog details at this time, please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 
	}

	private void validateAddRequest(AddDogRequest request) {
		if(request.getDetails().getAge()<=0) {
			throw new EnterpriseException("Age must be numeric and greater than 0.", HttpStatus.BAD_REQUEST);

		}
		if(request.getDetails().getHeight()== ""|| request.getDetails().getHeight() == null) {
			throw new EnterpriseException("All details, height, weight, age, color, breed are required.", HttpStatus.BAD_REQUEST);

		}
		if(request.getDetails().getBreed()== ""|| request.getDetails().getBreed() == null) {
			throw new EnterpriseException("All details, height, weight, age, color, breed are required.", HttpStatus.BAD_REQUEST);

		}
		if(request.getDetails().getWeight()== ""|| request.getDetails().getWeight() == null) {
			throw new EnterpriseException("All details, height, weight, age, color, breed are required.", HttpStatus.BAD_REQUEST);

		}
		if(request.getDetails().getColor()== ""|| request.getDetails().getColor() == null) {
			throw new EnterpriseException("All details, height, weight, age, color, breed are required.", HttpStatus.BAD_REQUEST);

		}
		if(request.getDetails().getName()== ""|| request.getDetails().getName() == null) {
			throw new EnterpriseException("All details, height, weight, age, color, breed are required.", HttpStatus.BAD_REQUEST);

		}
		
		if(request.getDetails().getBreed().length() >250 || request.getDetails().getColor().length() >250 || request.getDetails().getHeight().length() >250 || request.getDetails().getWeight().length() >250 || request.getDetails().getName().length() >250 ) {
			throw new EnterpriseException("A details is too long in length.", HttpStatus.BAD_REQUEST);

			
		}
 
	}

	private void validateEditRequest(EditDogRequest request) {
		if(request.getDetails().getAge()<=0) {
			throw new EnterpriseException("Age must be numeric and greater than 0.", HttpStatus.BAD_REQUEST);

		}
		if( request.getDetails().getBreed() !=null && request.getDetails().getBreed().length() >250 ){
			throw new EnterpriseException("A details is too long in length.", HttpStatus.BAD_REQUEST);

			
		}
		
		if( request.getDetails().getColor() !=null && request.getDetails().getColor().length()  >250 ){
			throw new EnterpriseException("A details is too long in length.", HttpStatus.BAD_REQUEST);

			
		}
		if( request.getDetails().getHeight() !=null && request.getDetails().getHeight().length()  >250 ){
			throw new EnterpriseException("A details is too long in length.", HttpStatus.BAD_REQUEST);

			
		}
		if( request.getDetails().getName() !=null && request.getDetails().getName().length()  >250 ){
			throw new EnterpriseException("A details is too long in length.", HttpStatus.BAD_REQUEST);

			
		}
		
		if( request.getDetails().getWeight() !=null && request.getDetails().getWeight().length()  >250 ){
			throw new EnterpriseException("A details is too long in length.", HttpStatus.BAD_REQUEST);

			
		}
	 
	 	 
	}
	
	@Override
	public DogDetailResponse addDogDetail(AddDogRequest request, long userId) {
		Dog editDog = new Dog();
		 
		editDog.setAge(request.getDetails().getAge());
		editDog.setBreed(request.getDetails().getBreed());
		editDog.setColor(request.getDetails().getColor());
		editDog.setHeight(request.getDetails().getHeight());
		editDog.setWeight(request.getDetails().getWeight());
		editDog.setName(request.getDetails().getName());
		
		//Should not need to do this! The Constraint Validation is not getting triggered for some reason;
		validateAddRequest(request);
		
		 Optional<User> user = userRepository.findById(userId);
		 //Alternatively, the userId could be got from the authentication principal and we would not pass the user id as a request path variable.
		 if(user.isEmpty()) {  
			 logger.error("user to be associated with new dog does not exist"+userId);
			 throw new EnterpriseException("User does not exist as per request", HttpStatus.BAD_REQUEST);  
		 }
		 
		 
		 
		 try {
			 Dog savedDog=dogRepository.save(editDog);
			 DogDetailResponse savedDogResponse = new DogDetailResponse(savedDog.getName(), savedDog.getHeight(), savedDog.getWeight(), savedDog.getBreed(), savedDog.getAge(), savedDog.getColor(), savedDog.getId());
			 return savedDogResponse; 
		 }
		 catch(Exception ex){
			 logger.error("An exception occurred editing Dog Info "+ex.getMessage());
			throw new EnterpriseException("We were not able to save the dog details at this time, please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
 
 			
		
}

 
 

 
