package com.userlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.dtos.LoginResponse;
import com.userlogin.dtos.LoginUserDto;
import com.userlogin.dtos.RegisterUserDto;
import com.userlogin.entities.User;
import com.userlogin.services.AuthenticationService;
import com.userlogin.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<User>  register(@RequestBody RegisterUserDto registerUserDto){
		User registeredUser= this.authenticationService.signup(registerUserDto);
		
		return ResponseEntity.ok(registeredUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
		
		User authenticatedUser = this.authenticationService.authenticate(loginUserDto);
		
		String jwtToken = this.jwtService.generateToken(authenticatedUser);
		
		LoginResponse loginResponse =  new LoginResponse();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(this.jwtService.getExpirationTime());
		
		return ResponseEntity.ok(loginResponse);
	}
}
