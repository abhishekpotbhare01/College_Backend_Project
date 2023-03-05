package com.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.UserRepository;
import com.app.dto.ErrorResponse;
import com.app.pojos.User;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/UserList")
	public List<User> getUserList() {
		System.out.println("In get user list");
		return userRepository.findAll();
	}

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody @Valid User user) {
		try {
			System.out.println("in add user");
			//userRepository.save(user);
			return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			ErrorResponse resp=new ErrorResponse("Adding User failed!!!!!", LocalDateTime.now());
			return new ResponseEntity<>("Duplicate User",HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/checkUser")
	public User userLogin(@RequestBody User user) {
		return userRepository.findUserByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
	}
	
	@PostMapping("/updateUserStatus")
	public String updateUserStatus(@RequestBody User usr) {
		User user = userRepository.findByUserId(usr.getUserId());
		if(user.getUserStatus().equals("active")) {
			user.setUserStatus("inactive");
		}else {
		user.setUserStatus("active");
		}
		userRepository.save(user);
		return null;
	}
	
	@PostMapping("/findByUserId")
	public User findUserById(@RequestBody User user) {
		return userRepository.findByUserId(user.getUserId());
	}
	
}









