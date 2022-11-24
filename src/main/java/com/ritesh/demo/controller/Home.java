package com.ritesh.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ritesh.demo.helper.JWTHelper;
import com.ritesh.demo.model.AjaxResponseBody;
import com.ritesh.demo.model.JwtRequest;
import com.ritesh.demo.model.User;
import com.ritesh.demo.repository.UserRepository;
import com.ritesh.demo.service.CustomUserDetailsService;



@RestController
public class Home {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JWTHelper jwtHelper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@RequestMapping("/welcome")
	public String welcome() {
		String text = "Hello World";
		return text;
	}
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
	
	@RequestMapping(value ="/logout",method = RequestMethod.POST)
	public ResponseEntity<?> logout(@RequestBody Map<String,String> body) throws Exception{
		System.out.println(body);
		jwtHelper.expire(body.get("token"));

		System.out.println(body);
		return ResponseEntity.ok("logout");
	
	}
	
	
	
	
	@RequestMapping(value ="/login",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody Map<String,String> body) throws Exception{
		AjaxResponseBody result = new AjaxResponseBody();

		
		System.out.println(body);
		System.out.println(body.get("username"));
		System.out.println(body.get("password"));
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(body.get("username"), body.get("password"))); 
			
		}catch (BadCredentialsException e) {
			return ResponseEntity
			          .badRequest()
			          .body("Bad Credentials");
		}
		UserDetails user =  this.customUserDetailsService.loadUserByUsername(body.get("username"));
		String token = this.jwtHelper.generateToken(user);
		System.out.println("TOKEN " +token);
		result.setToken(token);
		return ResponseEntity.ok(result);
	
	}
//	@RequestMapping(value = "/register", method =  RequestMethod.POST)
//	private ResponseEntity<?> register(@RequestBody JwtRequest jwtRequest){
//		if (userRepository.existsByUsername(jwtRequest.getUsername())) {
//		      return ResponseEntity
//		          .badRequest()
//		          .body("Error: Username is already taken!");
//		}
//		User user = new User(jwtRequest.getUsername(), jwtRequest.getPassword());
//		userRepository.save(user);
//		System.out.println("User Created Successfully");
//		return ResponseEntity.ok("User registered successfully!");
//		
//	}

}
