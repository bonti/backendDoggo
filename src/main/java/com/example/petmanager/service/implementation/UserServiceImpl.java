package com.example.petmanager.service.implementation; 
import java.util.ArrayList; 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.petmanager.service.UserService; 
import com.example.petmanager.response.bean.JwtAuthenticationResponse;
import com.example.petmanager.response.bean.BaseResponseBean;
import com.example.petmanager.security.JwtAuthenticationEntryPoint;
import com.example.petmanager.security.JwtTokenProvider;
import com.example.petmanager.exception.AuthenticationError;
import com.example.petmanager.repository.UserRepository;
import com.example.petmanager.entity.User;
import com.example.petmanager.request.bean.LoginRequest;
import com.example.petmanager.response.bean.ResponseWrapper;
import com.example.petmanager.response.bean.UserInfo;

import io.jsonwebtoken.Claims;


@Service
public class UserServiceImpl extends BaseService implements UserDetailsService , UserService{
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	  

	@Override
	public UserInfo loadUserByToken(Claims claims) { 
		  
			String userId = String.valueOf(claims.get("id"));
		  UserInfo userInfo = new UserInfo(userId, (String) claims.get("name"), 
					(String) claims.get("user-name"),	 
					(String) claims.get("password"));
			
			
		 	userInfo.setAccountNonExpired(true);
			userInfo.setAccountNonLocked(true);
			userInfo.setEnabled(true);
			userInfo.setCredentialsNonExpired(true); 
			return userInfo;
		
	}

	@Override
	public ResponseWrapper authenticateUser(LoginRequest request) {
	 
		String userName="";
		String password="";
		
		if (request != null) {
			userName = request.getUserName();
			password = request.getPassword();
		}
		User user = userRepository.findByUsernameAndPassword(userName, password);
		  
		// build userInfo if data is returned otherwise return error
		if(user!= null) {
			String name = user.getFullName();
			String userId =  user.getId().toString();
		 
	 		UserInfo userInfo =new UserInfo(userId, name, userName, password);
			userInfo.setAccountNonExpired(true);
			userInfo.setAccountNonLocked(true);
			userInfo.setCredentialsNonExpired(true);
			userInfo.setEnabled(true); 
			Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo, null);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			 
			String jwt = tokenProvider.generateToken(authentication,  name, null);
			userInfo = (UserInfo) authentication.getPrincipal();
			userInfo.setToken(new JwtAuthenticationResponse(jwt));
			 
			List<BaseResponseBean> responseData = new ArrayList<BaseResponseBean>(1);
			responseData.add(userInfo );
			ResponseWrapper responseWrapper = new ResponseWrapper(responseData);
			return responseWrapper;
			
		} else {
			
			logger.error("Authentication failed for username "+ userName );
			 		
			throw new AuthenticationError("Login unsuccessful. An error has ocurred.");
		}		
		  
	}

	@Override
	public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	} 
}

 
