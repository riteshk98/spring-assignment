package com.ritesh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ritesh.demo.helper.JWTHelper;
import com.ritesh.demo.model.JwtRequest;
import com.ritesh.demo.model.User;
import com.ritesh.demo.repository.UserRepository;
import com.ritesh.demo.service.CustomUserDetailsService;

@Controller
public class UserRegistrationController {
	
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JWTHelper jwtHelper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
	
	@PostMapping("/register")
	private ResponseEntity<?> register(User user){
		if (userRepository.existsByUsername(user.getUsername())) {
		      return ResponseEntity
		          .badRequest()
		          .body("Error: Username is already taken!");
		}
		userRepository.save(user);	
		return ResponseEntity.ok("User registered successfully!");
		
	}
	
	public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
