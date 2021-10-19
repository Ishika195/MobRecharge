package com.example.MobRecharge.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.dto.BankAccountRequest;
import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.entity.User;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.repository.BankAccountRepository;
import com.example.MobRecharge.repository.UserRepository;

@Service
public class BankAccountService {
      @Autowired
      BankAccountRepository bankAccountRepository;
      
      @Autowired
      UserRepository userRepository;
      
	  public BankAccount addBankAccount(BankAccountRequest bankAccountRequest) {
		  if(bankAccountRequest==null) {
				throw new RuntimeException("Empty object");
		}
		  User user = userRepository.findByUserId(bankAccountRequest.getUserId());
		  if(user==null) {
			  throw new ResourceNotFoundException("User Not Found");
		  }
		  BankAccount bankAccount = new BankAccount();
		  bankAccount.setAccountBalance(bankAccountRequest.getBalance());
		  bankAccount.setAccountHolder(bankAccountRequest.getHolderName());
		  bankAccount.setAccountNumber(bankAccountRequest.getNumber());
		  bankAccount.setUser(user);
		  
		  return  bankAccountRepository.save(bankAccount);
	 
	}
      public BankAccount getAccount(int id) {
    	  if (id <= 0) {
  			throw new InvalidArguementsException("Invalid Id");
  		}
    	  BankAccount bankAccount;
    	  try {
    		  bankAccount=bankAccountRepository.findByAccountId(id);
    	  }catch(EntityNotFoundException ex) {
    		  throw new ResourceNotFoundException("Bank Account Not Found");
    	  }
    	  return  bankAccount;
		
	}
	public List<BankAccount> getAllAccounts() {
	return bankAccountRepository.findAll();
	}
	public void deleteAccount(Integer id) {
		if (id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		BankAccount bankAccount=bankAccountRepository.findByAccountId(id);
		bankAccountRepository.delete(bankAccount);
	}
	public BankAccount updateAccount(Integer id, BankAccount bankAccount) {
		if(id==0 || bankAccount == null) {
			throw new InvalidArguementsException("bad arguement");
		}
		try {
		BankAccount exsistingBankAccount=bankAccountRepository.findByAccountId(id);
		if(exsistingBankAccount==null) {
			throw new RuntimeException("Bank Account not found");
		}
	
		if(bankAccount.getAccountNumber()!=0) {
			exsistingBankAccount.setAccountNumber(bankAccount.getAccountNumber());
		}
		
		if(bankAccount.getAccountHolder()!= null) {
			exsistingBankAccount.setAccountHolder(bankAccount.getAccountHolder());
		}
		if(bankAccount.getAccountBalance()!= 0.0f) {
			exsistingBankAccount.setAccountBalance(bankAccount.getAccountBalance());
		}
		if(bankAccount.getBankName()!= null) {
			exsistingBankAccount.setBankName(bankAccount.getBankName());
		}
		bankAccountRepository.save(exsistingBankAccount);
		return exsistingBankAccount;
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Plan not found");
		}
	}
	

}
