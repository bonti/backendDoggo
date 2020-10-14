package com.example.petmanager.util;

public interface PortalConstants {
 
	String HEADER_AUTH_TOKEN = "auth-token";

	String AUTH_TOKEN_DATA_MAP = "AUTH_TOKEN_DATA_MAP";

	String APP_NAME = "app-name"; 

	String[] SWAGGER_PATHS = new String[] { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
					"/configuration/security", "/swagger-ui.html", "/webjars/**" };
	
	String[] LOGIN_PATHS = new String[] {"/login/**", "/Login", "/Users/**", "/login" };
	
	String[] OTHER_ALLOWED_PATHS = new String[] {"/ClientConfig", "/clientConfig", "/clientconfig", "/appcache", "/appcache/clear", "/actuator/health", "/h2-console" };
	
	 
	String USERNAME = "user-name";
	
	String NAME = "name";
	
 	
	String USERID="id";
	
 	
	String PASSWORD = "password";
	
	String HEADER_AUTHORIZATION = "Authorization";
	
	String TOKEN_TYPE = "Bearer";
	 
	
 	
	String SORT_FIELD = "sortField";
	String SORT_ORDER = "sortOrder";
	String SORT_ORDER_ASC = "asc";
	String SORT_ORDER_DESC = "desc";
	
	String PAGE_SIZE = "pageSize";
	String PAGE_NUMBER = "pageNumber";
	
	String DEFAULT_PAGE_SIZE = "100";
	String DEFAULT_PAGE_NUMBER = "1";
	
	String ORACLE_SMALLEST_BIGINT = "-9223372036854775808";
	
	 	
	public static String ERROR = "error";
	
}