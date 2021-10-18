package com.example.MobRecharge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.service.BankAccountService;

@RestController
@RequestMapping("/MobRechargeApp/v1")

public class BankAccountController {
	@Autowired
	BankAccountService bankAccountService;
	@PostMapping("/BankAccounts")
	String addBankAccount (@RequestBody BankAccount bankaccount)
	{
	       bankAccountService.addBankAccount(bankaccount);
	       return "succefully add";
	}
	@GetMapping("/BankAccounts{id}")
	String getAccount() {
		return "getall accounts";
	}
	
	@GetMapping("/BankAccounts")
	String getAllAccounts() {
		return "getall accounts";
	}
	@PutMapping("/BankAccounts{id}")
	String updatAccount() {
		return "update account";
	}
	@DeleteMapping("/BankAccount{id}")
	String deleteAccount() {
		return "delete account";
	}
	

}
