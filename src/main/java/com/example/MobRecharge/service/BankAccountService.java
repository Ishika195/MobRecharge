package com.example.MobRecharge.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.dto.BankAccountRequest;
import com.example.MobRecharge.dto.BankAccountResponse;
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
		if (bankAccountRequest == null) {
			throw new RuntimeException("Empty object");
		}

		User user = userRepository.findByUserId(bankAccountRequest.getUserId());
		if (user == null) {
			throw new ResourceNotFoundException("User Not Found");
		}
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBalance(bankAccountRequest.getBalance());
		bankAccount.setHolderName(bankAccountRequest.getHolderName());
		bankAccount.setNumber(bankAccountRequest.getNumber());
		bankAccount.setBankName(bankAccountRequest.getBankName());
		bankAccount.setUserId(user);

		return bankAccountRepository.save(bankAccount);

	}

	public BankAccountResponse getAccount(int id) {
		if (id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		BankAccount bankAccount;
		try {
			bankAccount = bankAccountRepository.findByAccountId(id);
		} catch (EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Bank Account Not Found");
		}
		return convertBankAccountToBankAccountResponse(bankAccount);

	}
	
	BankAccountResponse convertBankAccountToBankAccountResponse(BankAccount bankAccount) {
		BankAccountResponse bankAccountResponse = new BankAccountResponse();
		bankAccountResponse.setAccountId(bankAccount.getAccountId());
		bankAccountResponse.setBalance(bankAccount.getBalance());
		bankAccountResponse.setBankName(bankAccount.getBankName());
		bankAccountResponse.setHolderName(bankAccount.getHolderName());
		bankAccountResponse.setNumber(bankAccount.getNumber());
		bankAccountResponse.setUserId(bankAccount.getUserId().getUserId());
		bankAccountResponse.setUsername(bankAccount.getUserId().getUsername());
		return bankAccountResponse;
	}

	public List<BankAccount> getAllAccounts() {
		return bankAccountRepository.findAll();
	}

	public void deleteAccount(Integer id) {
		if (id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		BankAccount bankAccount = bankAccountRepository.findByAccountId(id);
		bankAccountRepository.delete(bankAccount);
	}

	public BankAccount updateAccount(Integer id, BankAccount bankAccount) {
		if (id == 0 || bankAccount == null) {
			throw new InvalidArguementsException("bad arguement");
		}
		try {
			BankAccount exsistingBankAccount = bankAccountRepository.findByAccountId(id);
			if (exsistingBankAccount == null) {
				throw new RuntimeException("Bank Account not found");
			}

			if (bankAccount.getNumber() != 0) {
				exsistingBankAccount.setNumber(bankAccount.getNumber());
			}

			if (bankAccount.getHolderName() != null) {
				exsistingBankAccount.setHolderName(bankAccount.getHolderName());
			}
			if (bankAccount.getBalance() != 0.0f) {
				exsistingBankAccount.setBalance(bankAccount.getBalance());
			}
			if (bankAccount.getBankName() != null) {
				exsistingBankAccount.setBankName(bankAccount.getBankName());
			}
			bankAccountRepository.save(exsistingBankAccount);
			return exsistingBankAccount;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Plan not found");
		}
	}

	public List<BankAccount> getAllAccountByUser(int id) {
		if (id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		return bankAccountRepository.findByUserId(id);
	}
}
