package com.example.MobRecharge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.MobRecharge.entity.Transaction;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.service.TransactionService;

@RestController
@RequestMapping("/MobRechargeApp/v1")
public class TransactionController {
   @Autowired
   TransactionService transactionService;
   @PostMapping("/users/transaction")
   String getTransaction(@RequestBody Transaction transaction) {
	   
	   return "Successfully done";
   }
   @GetMapping("/users/transaction/{id}")
   ResponseEntity<Transaction> getTransactionDetail(@PathVariable Integer id) {
	   
	   try{
		   return  ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionDetail(id));
	   }catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found", exc);
		}catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
   }
   
   
}
