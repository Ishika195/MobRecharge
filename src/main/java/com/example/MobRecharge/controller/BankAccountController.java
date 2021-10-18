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

import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.service.BankAccountService;

@RestController
@RequestMapping("/MobRechargeApp/v1")

public class BankAccountController {
	@Autowired
	BankAccountService bankAccountService;

	@PostMapping("/BankAccounts")
	ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankaccount) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.addBankAccount(bankaccount));
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Bank Account Not Saved", exc);
		}
	}

	@GetMapping("/BankAccounts")
	ResponseEntity<List<BankAccount>> getAllAccounts() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getAllAccounts());
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Accounts Not Found", exc);
		}
	}

	@GetMapping("/BankAccounts/{id}")
	ResponseEntity<BankAccount> getAccount(@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getAccount(id));
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Account Not Found", exc);
		}
	}

	@DeleteMapping("/BankAccounts/{id}")
	ResponseEntity<String> deleteAccount(@PathVariable Integer id) {
		try {
			bankAccountService.deleteAccount(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Succefully");
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Account Not found", exc);
		}
	}

	@PutMapping("/BankAccounts/{id}")
	ResponseEntity<BankAccount> updatAccount(@PathVariable Integer id, @RequestBody BankAccount bankAccount) {
		System.out.println(bankAccount);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.updateAccount(id, bankAccount));

		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank Account Not Found", exc);
		}
	}
}
