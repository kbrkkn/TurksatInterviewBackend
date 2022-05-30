package com.turksat.belgenet.interview.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turksat.belgenet.interview.requests.UserRequest;
import com.turksat.belgenet.interview.responses.AuthResponse;
import com.turksat.belgenet.interview.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
    
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());
		Authentication authToken=authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(authToken);
		String jwtToken=jwtTokenProvider.generateJwtToken(authToken);
		AuthResponse authResponse=new AuthResponse();
		authResponse.setMessage("Bearer "+jwtToken);
		return authResponse;
	}
}
