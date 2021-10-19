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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.MobRecharge.dto.BankAccountRequest;
import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.service.BankAccountService;

@RestController
@RequestMapping("/mobRechargeApp/v1")

public class BankAccountController {
	@Autowired
	BankAccountService bankAccountService;

	@PostMapping("/bankAccount")
	ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccountRequest bankaccount) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.addBankAccount(bankaccount));
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@GetMapping("/bankAccount")
	ResponseEntity<List<BankAccount>> getAllAccounts() {
		List<BankAccount> accounts= bankAccountService.getAllAccounts();
			return ResponseEntity.status(HttpStatus.OK).body(accounts);
		
	}

	@GetMapping("/bankAccount/{id}")
	ResponseEntity<BankAccount> getAccount(@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getAccount(id));
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Account Not Found", exc);
		}catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@DeleteMapping("/bankAccount/{id}")
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
	

	@PutMapping("/BankAccounts/{id}")
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
