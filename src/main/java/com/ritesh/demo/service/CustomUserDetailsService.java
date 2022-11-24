package com.ritesh.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ritesh.demo.model.User;
import com.ritesh.demo.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			
			User user  = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No User Found"));
//			return new User("ritesh", "password", new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("No User Found");
		
		return UserDetailsImpl.build(user);
	}

}
