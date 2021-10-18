package com.example.MobRecharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping("/BankAccounts/{id}")
	BankAccount getAccount(@PathVariable Integer id) {
		return bankAccountService.getAccount(id);
	}
	@DeleteMapping("/BankAccounts/{id}")
	String deleteAccount(@PathVariable Integer id) {
		bankAccountService.deleteAccount(id);
		return "Succefully Deleted";
	}
	
	
	@PutMapping("/BankAccounts/{id}")
	BankAccount updatAccount(@PathVariable Integer id,@RequestBody BankAccount bankAccount) {
		bankAccount.setAccountId(id);
		return bankAccountService.updateAccount(id,bankAccount);
	}
	
	

}
