package com.example.petmanager.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.petmanager.Configuration;
import com.example.petmanager.response.bean.UserInfo;
import com.example.petmanager.util.PortalConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

 
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  
    @Autowired
    private Configuration configuration;
   
    /**
     * This method generates JWT Token once user is authenticated.
     * @param authentication
     * @param clientName
     * @param name
     * @param userRole
     * @return
     */
	public String generateToken(Authentication authentication,  String name, String roleId) {

		UserInfo userPrincipal = (UserInfo) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + configuration.getAuthSecretExpirationTime());

		Map<String, Object> claims = new HashMap<>(4);
		claims.put(PortalConstants.USERID, userPrincipal.getId()); 
		claims.put(PortalConstants.USERNAME, userPrincipal.getUsername());
		claims.put(PortalConstants.NAME, name);
	  
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256,
						configuration.getKey()) //Would normally get this from a util and use a secrets service like AWS Secrets Manager
				.compact();
	}
    
    /**
     * Method to fetch user from jwt token.
     * @param token
     * @return
     */
    public Claims getUserFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(configuration.getKey())
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    /**
     * Method to validated jwt token.
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(configuration.getKey()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}