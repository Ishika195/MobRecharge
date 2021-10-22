package com.example.MobRecharge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.MobRecharge.dto.TransactionRequest;
import com.example.MobRecharge.dto.TransactionResponse;
import com.example.MobRecharge.entity.Transaction;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.service.TransactionService;

@RestController
@RequestMapping("/mobrecharge/v1")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/user/transaction/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	ResponseEntity<List<Transaction>>getTransactionHistory(@PathVariable Integer id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionService.transactionHistory(id));
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transactions Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}
	
	@GetMapping("/transaction/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	ResponseEntity<TransactionResponse> getTransactionDetail(@PathVariable Integer id) {


		try {
			return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionDetail(id));
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@PostMapping("/user/transaction/makepayment")
	@PreAuthorize("hasRole('USER')")
	ResponseEntity<TransactionResponse> makePayment(@Valid @RequestBody TransactionRequest transactionRequest) {

		try {
			TransactionResponse transactionResponse = transactionService.makePayment(transactionRequest);
			return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, exc.getMessage(), exc);
		}
	}

	
}
