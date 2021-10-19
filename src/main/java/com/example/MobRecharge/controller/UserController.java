package com.example.MobRecharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.example.MobRecharge.entity.User;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.service.UserService;

@RestController
@RequestMapping("/mobRechargeApp/v1")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/user")
	ResponseEntity<String> addNewUser(@RequestBody User user) {
		try {
			Integer id = userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully with id: "+id);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}
	
	@GetMapping("/user")
	ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
	}
	
	@DeleteMapping("/user/{id}")
	ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body("User delete successfully");
		}catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		} catch (IllegalArgumentException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}
	
	@PutMapping("/user/{id}")
	ResponseEntity<User> updateUserProfile(@PathVariable int id, @RequestBody User user) {
		System.out.println(user);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));

		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}
}
