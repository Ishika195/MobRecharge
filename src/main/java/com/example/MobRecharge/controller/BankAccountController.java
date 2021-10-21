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

import com.example.MobRecharge.dto.BankAccountRequest;
import com.example.MobRecharge.dto.BankAccountResponse;
import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.service.BankAccountService;

@RestController
@RequestMapping("/mobrecharge/v1")

public class BankAccountController {
	@Autowired
	BankAccountService bankAccountService;

	@PostMapping("/account")
	@PreAuthorize("hasRole('USER') ")
	ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccountRequest bankaccount) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.addBankAccount(bankaccount));
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@GetMapping("/account")
	@PreAuthorize("hasRole('USER') ")
	ResponseEntity<List<BankAccount>> getAllAccounts() {
		List<BankAccount> accounts= bankAccountService.getAllAccounts();
			return ResponseEntity.status(HttpStatus.OK).body(accounts);
		
	}

	@GetMapping("/account/{id}")

	@PreAuthorize("hasRole('USER')")
	ResponseEntity<BankAccountResponse> getAccount(@PathVariable Integer id) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getAccount(id));
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Account Not Found", exc);
		}catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@DeleteMapping("/account/{id}")
	@PreAuthorize("hasRole('USER')")
	ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
		try {
			bankAccountService.deleteAccount(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
		}catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		} catch (IllegalArgumentException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}
	

	@PutMapping("/account/{id}")
	@PreAuthorize("hasRole('USER')")
	ResponseEntity<BankAccount> updatAccount(@PathVariable Integer id, @RequestBody BankAccount bankAccount) {
		System.out.println(bankAccount);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.updateAccount(id, bankAccount));

		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		} catch (IllegalArgumentException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}
}
