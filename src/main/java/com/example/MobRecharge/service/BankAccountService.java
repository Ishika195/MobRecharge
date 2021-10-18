package com.example.MobRecharge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.repository.BankAccountRepository;

@Service
public class BankAccountService {
      @Autowired
      BankAccountRepository bankAccountRepository;
	  public String addBankAccount(BankAccount bankaccount) {
		  bankAccountRepository.save(bankaccount);
	 return "ok";
		
	}
	

}
