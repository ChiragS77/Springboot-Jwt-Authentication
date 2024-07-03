package com.userlogin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userlogin.entities.User;
import com.userlogin.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> allUsers(){
		
		List<User> users = new ArrayList<>();
		
		this.userRepository.findAll().forEach(users::add);
		
//		List<User> list = this.userRepository.findAll();
		
		return users;
	}
	
	
}
