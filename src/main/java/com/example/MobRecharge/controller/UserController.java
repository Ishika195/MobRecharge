package com.example.MobRecharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.entity.Plan;
import com.example.MobRecharge.entity.User;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.service.BankAccountService;
import com.example.MobRecharge.service.UserService;

@RestController
@RequestMapping("/mobrecharge/v1")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	BankAccountService bankAccountService;

	@PostMapping("/user")
	@PreAuthorize("hasRole('USER')")
	ResponseEntity<String> addNewUser(@RequestBody User user) {
		try {
			Long id = userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully with id: " + id);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
	}

	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	ResponseEntity<String> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body("User delete successfully");
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		} catch (IllegalArgumentException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@GetMapping("/user/account/{id}")
	@PreAuthorize("hasRole('USER')")
	ResponseEntity<List<BankAccount>> getUserAccounts(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getAllAccountByUser(id));
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
		}
	}

	@GetMapping("/user/plan/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	ResponseEntity<List<Plan>> getUserPlans(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAllPlans(id));
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@PutMapping("/user/{id}")
	@PreAuthorize("hasRole('USER') ")
	ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
		System.out.println(user);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));

		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}
}
