package com.example.petmanager.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.petmanager.response.bean.UserInfo;
import com.example.petmanager.service.UserService;
import com.example.petmanager.util.PortalConstants;
 
import io.jsonwebtoken.Claims;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;
 
	@Autowired 
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			if (StringUtils.hasText(jwt)) {
				if (tokenProvider.validateToken(jwt)) {
					Claims claim = tokenProvider.getUserFromJWT(jwt); 
					 
					UserInfo userInfo = (UserInfo) userService.loadUserByToken(claim);
					
					// add specific details to request  
					request.setAttribute(PortalConstants.USERNAME, userInfo.getUsername()); 
					request.setAttribute(PortalConstants.HEADER_AUTH_TOKEN, jwt);
					
					 
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userInfo, userInfo.getPassword(), null);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}  
		} catch (Exception ex) {
			logger.error("Could not set user authentication in security context.", ex);
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(PortalConstants.HEADER_AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(PortalConstants.TOKEN_TYPE + " ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
