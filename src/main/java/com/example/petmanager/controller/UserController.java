package com.example.petmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.petmanager.exception.AuthenticationError; 
import com.example.petmanager.request.bean.LoginRequest;
import com.example.petmanager.response.bean.ResponseWrapper;
 
import com.example.petmanager.service.UserService;
import com.example.petmanager.util.PortalConstants;
 
@RestController
@RequestMapping(value = "/login")
public class UserController extends BaseController{ 
	
	@Autowired
	private UserService userService;
	
 
	/***
	 * This method creates a authenticates a user
	 * @param username
	 * @param password
	 * @return
	 */
	 
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)  throws AuthenticationError  {		
		return ResponseEntity.ok(userService.authenticateUser(loginRequest));
	}
	
}
