package com.ritesh.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritesh.demo.model.User;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID =1L;
	
	  private String id;

	  private String username;


	  @JsonIgnore
	  private String password;

	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(String id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		    this.id = id;
		    this.username = username;
		    this.password = password;
		    this.authorities = authorities;
		    
		  }
	 public static UserDetailsImpl build(User user) {
		    return new UserDetailsImpl(
		        user.getId(), 
		        user.getUsername(), 
		        user.getPassword(),
		        new ArrayList<>()
		        );
		  }
	 
	public String getId() {
		return id;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
	@Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }
	
	

}
