package com.example.petmanager.response.bean;

import com.example.petmanager.util.PortalConstants; 

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = PortalConstants.TOKEN_TYPE;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}