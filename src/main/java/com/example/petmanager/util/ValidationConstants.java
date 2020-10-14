package com.example.petmanager.util;

public interface ValidationConstants {

	public interface Regex {
		String ALPHANUMERIC_EN = "^[a-zA-Z0-9 ]*$";
		String ALPHANUMERIC_EN_UNDERSCORE = "^[a-zA-Z0-9 _]*$";
		 
	}
	
	public interface Max {
		int TAX_TYPE = 50;
		int TAX_CERTIFICATE_DESCRIPTION = 25;
		int EXEMPTION_REASON_DECSRIPTION = 25;
	}

	public interface Min {
		
	}
}
