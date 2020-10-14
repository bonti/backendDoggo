package com.example.petmanager;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("examplepetapi")
public class Configuration {

	private long authSecretExpirationTime;
	
	 
	private String key;
	 
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public long getAuthSecretExpirationTime() {
		return authSecretExpirationTime;
	}
	public void setAuthSecretExpirationTime(long authSecretExpirationTime) {
		this.authSecretExpirationTime = authSecretExpirationTime;
	}
	 
	
	  
}