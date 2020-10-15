package com.example.petmanager.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.petmanager.util.PortalConstants;
import com.example.petmanager.exception.EnterpriseException;
import com.example.petmanager.response.bean.DogListResponse;
import com.example.petmanager.response.bean.ResponseWrapper;
import com.example.petmanager.response.bean.StatusBean;
import com.example.petmanager.response.bean.UserInfo;
import com.example.petmanager.service.DogService;;
 
 
@Validated
@RestController
@RequestMapping(value = "/doggos-list")
public class DoggoListController extends BaseController{ 
	
	@Autowired
	private DogService dogService;
	 
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDogsByUser( 
			@RequestHeader(value = PortalConstants.HEADER_AUTHORIZATION) String authentication) {		
		
		Authentication authenticationObj = SecurityContextHolder.getContext().getAuthentication();
		long userId= Long.parseLong(((UserInfo)authenticationObj.getPrincipal()).getId());
 		
		try {
			 DogListResponse dogList = dogService.getDogsByUser(userId); 
			return ResponseEntity.ok(dogList);
			}
			catch(Exception ex) {
			
				if(ex.getClass() == EnterpriseException.class) {
					HttpStatus status = ((EnterpriseException) ex).getStatus(); 
					StatusBean sb = StatusBean.getFailure( status,   ex.getMessage());
					return new ResponseEntity(  new ResponseWrapper(sb), status);
					}
					StatusBean sb = StatusBean.getFailure( HttpStatus.BAD_REQUEST,   ex.getMessage()); 
					return new ResponseEntity( new ResponseWrapper(sb), HttpStatus.BAD_REQUEST); 
			}
	} 
	  
}
