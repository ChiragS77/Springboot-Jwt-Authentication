package com.userlogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.entities.User;
import com.userlogin.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/me")
	public ResponseEntity<User> authenticatedUser(){
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
	
		User currUser = (User)authentication.getPrincipal();
		
		return ResponseEntity.ok(currUser);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> allUsers(){
		List<User> users = this.userService.allUsers();
		
		return ResponseEntity.ok(users);
	}
	
	
	
}
