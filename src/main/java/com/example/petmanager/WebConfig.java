package com.example.petmanager;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry; 
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 


@Configuration
public class WebConfig {
	 
 
	private static int CONNECTION_TIMEOUT	= 10000;
	private static int READ_TIMEOUT 		= 20000;
	
	@Resource
	public Environment environment;

	@Bean
    public WebMvcConfigurer webConfigurer() {
        return new WebMvcConfigurer() {
        	/**
        	 * Method to enable CORS at application level.
        	 * @return WebMvcConfigurer
        	 */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
            
            /**
        	 * Method to have URLs as case-insensitive
        	 */
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
            	AntPathMatcher matcher = new AntPathMatcher();
                matcher.setCaseSensitive(false);
                configurer.setPathMatcher(matcher);
            }
            
           
           
        };
    }
	
	/**
	 * Method to enable logging incoming requests.
	 * @return
	 */
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeClientInfo(true);
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(true);
	    return loggingFilter;
	}
	
	 
   
     
    
 
}
