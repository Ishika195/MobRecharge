package com.example.MobRecharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.service.BankAccountService;

@RestController
@RequestMapping("/MobRechargeApp/v1")

public class BankAccountController {
	@Autowired
	BankAccountService bankAccountService;
	@PostMapping("/BankAccounts")
	BankAccount addBankAccount (@RequestBody BankAccount bankaccount)
	{
	       return bankAccountService.addBankAccount(bankaccount);
	       
	}
	@GetMapping("/BankAccounts")
	List<BankAccount>getAllAccounts() {
		 return bankAccountService.getAllAccounts();
	}
	@DeleteMapping("/BankAccount{id}")
	String deleteAccount(@RequestParam Integer id) {
		bankAccountService.deleteAccount(id);
		return "Succefully Deleted";
	}
	
	@GetMapping("/BankAccounts{id}")
	BankAccount getAccount(@RequestParam Integer id) {
		return bankAccountService.getAccount(id);
	}
	/*
	
	@PutMapping("/BankAccounts{id}")
	String updatAccount() {
		return "update account";
	}
	
	*/

}
