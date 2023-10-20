package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Credentials;
import com.repository.CredentialsRepository;

@RestController
@RequestMapping("/users")
public class CredentialsController {
	
	@Autowired
	private CredentialsRepository repository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Credentials users){
		Credentials savedUser =null;
		ResponseEntity response = null;
		
	try {
	String hashPwd = passwordEncoder.encode(users.getPwd());
	users.setPwd(hashPwd);
	savedUser = repository.save(users);
	if(savedUser.getId()>0){
	response = ResponseEntity.status(HttpStatus.CREATED).body("User is Successfully Added");
	}
	}catch (Exception e){
	response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	.body("Exception occured due to "+ e.getMessage());
	}
	
	return response;
	}

}
