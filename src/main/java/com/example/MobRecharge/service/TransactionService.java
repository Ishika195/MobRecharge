package com.example.MobRecharge.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.entity.Transaction;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.repository.TransactionRepository;

@Service
public class TransactionService {
     @Autowired
     TransactionRepository transactionRepository;
	public Transaction getTransactionDetail(Integer id) {
		if(id<=0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		Transaction transaction;
		try {
			transaction=transactionRepository.getById(id);
		}catch(EntityNotFoundException ex) {
  		  throw new ResourceNotFoundException("Bank Account Not Found");
  	  }
		return transaction;
	}

}
