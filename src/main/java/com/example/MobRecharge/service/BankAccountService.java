package com.example.MobRecharge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.repository.BankAccountRepository;

@Service
public class BankAccountService {
      @Autowired
      BankAccountRepository bankAccountRepository;
	  public BankAccount addBankAccount(BankAccount bankaccount) {
		return  bankAccountRepository.save(bankaccount);
	 
	}
      public BankAccount getAccount(Integer id) {
    	  return bankAccountRepository.findById(id);
		
	}
	public List<BankAccount> getAllAccounts() {
	return bankAccountRepository.findAll();
	}
	public void deleteAccount(Integer id) {
		BankAccount bankAccount=bankAccountRepository.findById(id);
		bankAccountRepository.delete(bankAccount);
	}
	

}
