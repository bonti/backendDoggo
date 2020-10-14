package com.example.petmanager.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.petmanager.util.PortalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	@Qualifier(DispatcherServlet.HANDLER_EXCEPTION_RESOLVER_BEAN_NAME)
	HandlerExceptionResolver handlerExceptionResolver;
	
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException,AuthenticationException {
       
         
        //special case for when spring HttpFirewall throws an RequestRejectedException
        Object errorObj = httpServletRequest.getAttribute("javax.servlet.error.exception");
        if (errorObj != null && errorObj instanceof RequestRejectedException) {
        	logger.error("Spring HttpFirewall Failure", ((Exception)errorObj).getMessage());
        	handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, 
        																null, (Exception)errorObj);
        } else {
        	logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
        	handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, e);
        }
       
        
    }
}