package com.example.petmanager.service;
 

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.petmanager.request.bean.LoginRequest;
import com.example.petmanager.response.bean.ResponseWrapper;
import com.example.petmanager.response.bean.UserInfo;

import io.jsonwebtoken.Claims;

public interface UserService {
	
	 
	public ResponseWrapper authenticateUser(LoginRequest request);

	 //Added for security config
    
	UserDetails loadUserByToken(Claims claims);
    
   
	UserInfo loadUserByUsername(String username) throws UsernameNotFoundException;
}
