package com.example.petmanager.response.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Could potentially add Role/Permissions here for authorization
public class UserInfo extends BaseResponseBean  implements UserDetails {

	private static final long serialVersionUID = -5842394387533810972L;

	private String id;

    private String name;

    private String username; 
    
 
    @JsonIgnore
    private String password;
    
    private boolean accountNonExpired;
    
    private boolean credentialsNonExpired;
    
    private boolean accountNonLocked;
    
    private boolean enabled; 
    
    private JwtAuthenticationResponse token;
     
    public UserInfo(String id, String name, String username,   
    			String password) {
        this.id = id;
        this.name = name;
        this.username = username; 
        this.password = password; 
    }

 
 


	public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

   
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public JwtAuthenticationResponse getToken() {
		return token;
	}

	public void setToken(JwtAuthenticationResponse token) {
		this.token = token;
	}

	public void setId(String id) {
         this.id=id;
    }     

 

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	 

}