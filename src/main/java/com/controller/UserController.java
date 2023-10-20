package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.model.Credentials;
import com.repository.CredentialsRepository;
import com.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	

	@PostMapping("/save")
	public ResponseEntity<User> createNewUser(@RequestBody User user){
		User saveUser = service.saveUser(user);
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public String updateUserById(@RequestBody User user) {
		int updateUser = service.updateUser(user.getId(), user);
		if(updateUser == 0) {  
			return "user not found";
		}
		return "user updated successfully";
	}
	
	@DeleteMapping ("/delete/{id}")
	public String deleteUserById(@PathVariable("id") int userId) {
		String deleteUser = service.deleteUser(userId);
		return "deleted";
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int userId){
		User findUserById = service.findUserById(userId);
		return new ResponseEntity<User>(findUserById,HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public List<User> getAllUser(){
		List<User> findAll = service.findAll();
		return findAll;
	}
	
}
