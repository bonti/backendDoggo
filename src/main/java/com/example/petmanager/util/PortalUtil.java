package com.example.petmanager.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.petmanager.response.bean.UserInfo;

@Service
public class PortalUtil {

	public static final DateFormat 	epDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	 
	
	private static final Logger logger = LoggerFactory.getLogger(PortalUtil.class); 

	@Autowired
	static Environment environment;

	@Autowired
	public void setEnvironment(Environment environment) {
		PortalUtil.environment = environment;
	}
	
	

	/**
	 * Checks the string if it is not null and empty.
	 * @param input
	 * @return
	 */
	public static boolean isStringNotEmpty(String input) {
		return (input != null && !input.trim().equals(""));
	}

 

 

	
	/**
	 * deep clone java object
	 */
	public static Object deepClone(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			logger.error("Exception to deep clone object", e);
			return null;
		}
	}
	
	/**
	 * Method to get logged in user.
	 * @return userInfo
	 */
	public static UserInfo getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserInfo userInfo = (UserInfo) authentication.getPrincipal();
		return userInfo;
	}

	public static String ConvertToJson(Object object) {
	 	ObjectMapper Obj = new ObjectMapper(); 
        try { 
            String jsonStr = Obj.writeValueAsString(object); 
            return jsonStr;
        } 
        catch (IOException e) { 
            logger.error("Exception while converting to JSON string.", e);
        } 
        return null;
	}
	
 
	public static String getFormattedDate (String strDate) {

		String newDateStr = strDate;

		if (StringUtils.isNotBlank(strDate)) {
			try {
				Date date = epDateFormatter.parse(strDate);
				newDateStr = epDateFormatter.format(date);
			} catch (ParseException e) {
				 
					logger.warn("Unable to parse Date: {}",strDate);
				}
				
			
		}
		return newDateStr;
	}
}
