package com.example.petmanager.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; 
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.petmanager.util.PortalConstants;
import com.example.petmanager.exception.EnterpriseException;
import com.example.petmanager.response.bean.DogListResponse; 
import com.example.petmanager.service.DogService;;
 
 
@Validated
@RestController
@RequestMapping(value = "/doggos-list")
public class DoggoListController extends BaseController{ 
	
	@Autowired
	private DogService dogService;
	 
	@GetMapping(value="/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDogsByUser(@PathVariable(value="userId") long userId,
			@RequestHeader(value = PortalConstants.HEADER_AUTHORIZATION) String authentication) {		
		try {
			 DogListResponse dogList = dogService.getDogsByUser(userId); 
			return ResponseEntity.ok(dogList);
			}
			catch(Exception ex) {
				if(ex.getClass() == EnterpriseException.class) {
					return new ResponseEntity(ex.getMessage(), ((EnterpriseException) ex).getStatus());
					}
					return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

			}
	} 
	  
}
