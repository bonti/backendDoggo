package com.example.petmanager.controller;
 
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.petmanager.util.PortalConstants;
import com.example.petmanager.exception.EnterpriseException;
import com.example.petmanager.request.bean.AddDogRequest;
import com.example.petmanager.request.bean.EditDogRequest;
import com.example.petmanager.response.bean.DogDetailResponse;
import com.example.petmanager.response.bean.ResponseWrapper;
import com.example.petmanager.response.bean.UserInfo;
import com.example.petmanager.service.DogService;
 
 
@Validated
@RestController
@RequestMapping(value = "/doggo")
public class DogController extends BaseController{ 
	
	@Autowired
	private DogService dogService;
	 
	 
	
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDogDetails(@PathVariable(value="id") long id,
			@RequestHeader(value = PortalConstants.HEADER_AUTHORIZATION) String authentication) {	
		 
		 
		try {
			DogDetailResponse dogDetail = dogService.getDogDetailById(id); 
			return ResponseEntity.ok(dogDetail);
		}
		catch(Exception ex) {
			if(ex.getClass() == EnterpriseException.class) {
			return new ResponseEntity(ex.getMessage(), ((EnterpriseException) ex).getStatus());
			}
			return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
	 	 
		
	} 
	 
		 
 	/***
	 * This method adds an existing dog for a user
	 * @param userId
	 * @param dogId
	 * @param authentication
	 * @return
	 */
 	@PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addDog( 
		@Valid @RequestBody AddDogRequest addDogRequest,  
		@RequestHeader(value = PortalConstants.HEADER_AUTHORIZATION) String authentication) {
 		Authentication authenticationObj = SecurityContextHolder.getContext().getAuthentication();
 	 
 		long userId= Long.parseLong(((UserInfo)authenticationObj.getPrincipal()).getId());
 		try {
 			DogDetailResponse dogDetail = dogService.addDogDetail(addDogRequest, userId); 
 			return new ResponseEntity(dogDetail, HttpStatus.OK);
 			}
 		catch(Exception ex) {
 			if(ex.getClass() == EnterpriseException.class) {
 				return new ResponseEntity(ex.getMessage(), ((EnterpriseException) ex).getStatus());
 				}
 				return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
 		} 
    }
	 	
 
		 
 	/***
	 * This method edits an existing dog for a user
	 
	 * @param dogId
	 * @param authentication
	 * @return
	 */
 	@PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editDog( 
		@Valid @RequestBody EditDogRequest editDogRequest, 
		@PathVariable(value="id") long id,
		@RequestHeader(value = PortalConstants.HEADER_AUTHORIZATION) String authentication) {

 		try {
 			DogDetailResponse dogDetail = dogService.editDogDetail(editDogRequest, id); 
 			return ResponseEntity.ok(dogDetail);
 			}
 		catch(Exception ex) {
 			if(ex.getClass() == EnterpriseException.class) {
 				return new ResponseEntity(ex.getMessage(), ((EnterpriseException) ex).getStatus());
 				}
 				return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

 		}  
    }

	
}
