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
      public BankAccount getAccount(int id) {
    	  return  bankAccountRepository.findByAccountId(id);
		
	}
	public List<BankAccount> getAllAccounts() {
	return bankAccountRepository.findAll();
	}
	public void deleteAccount(Integer id) {
		BankAccount bankAccount=bankAccountRepository.findByAccountId(id);
		bankAccountRepository.delete(bankAccount);
	}
	public BankAccount updateAccount(Integer id, BankAccount bankAccount) {
		bankAccountRepository.save(bankAccount);
		return bankAccount;
	}
	

}
