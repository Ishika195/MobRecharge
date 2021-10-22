package com.example.MobRecharge.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.dto.TransactionRequest;
import com.example.MobRecharge.dto.TransactionResponse;
import com.example.MobRecharge.entity.BankAccount;
import com.example.MobRecharge.entity.Offer;
import com.example.MobRecharge.entity.Plan;
import com.example.MobRecharge.entity.Transaction;
import com.example.MobRecharge.entity.User;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.repository.PlanRepository;
import com.example.MobRecharge.repository.TransactionRepository;
import com.example.MobRecharge.repository.BankAccountRepository;
import com.example.MobRecharge.repository.UserRepository;

@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	BankAccountRepository bankAccountRepository;

	public List<Transaction> transactionHistory(Integer id) {
		if (id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		return transactionRepository.findByUserId(id);
	}

	public TransactionResponse getTransactionDetail(Integer id) {
		if (id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		Transaction transaction;
		try {
			transaction = transactionRepository.getById(id);
		} catch (EntityNotFoundException ex) {
			throw new ResourceNotFoundException("Bank Account Not Found");
		}
		return convertTransactionToTrasactionResponse(transaction);
	}

	@Transactional
	public TransactionResponse makePayment(TransactionRequest transactionRequest) {

		if (transactionRequest == null) {
			throw new InvalidArguementsException("Empty transaction request");
		}
		User user = userRepository.findByUserId(transactionRequest.getUserId());
		if (user == null) {
			throw new ResourceNotFoundException("User not found");
		}
		Plan plan = planRepository.findByPlanId(transactionRequest.getPlanId());

		if (plan == null) {
			throw new ResourceNotFoundException("plan not found");
		}
		BankAccount bankAccount = bankAccountRepository.findByNumber(transactionRequest.getAccountNumber());

		if (bankAccount == null) {
			throw new ResourceNotFoundException("account not found");
		}

		if (bankAccount.getBalance() < plan.getPrice()) {
			throw new RuntimeException("Not enough balance");
		}
		float totalAmount = 0.f;

		if (plan.getOffer() != null) {
			Offer offer = plan.getOffer();
			if (plan.getPrice() > offer.getMinAmount()) {
				 totalAmount = plan.getPrice() - plan.getPrice() * (offer.getDiscount() / 100);
				if (totalAmount > offer.getMaxAmount()) {
					totalAmount = offer.getMaxAmount();
				}
			}

		}
		Transaction transaction = new Transaction();
		transaction.setAmount(totalAmount);
		transaction.setModOfPayment(transactionRequest.getModOfPayment());
		transaction.setUserId(user);
		transaction.setPlanId(plan);
		transaction.setAccountId(bankAccount);
		transaction.setMobileNumber(transactionRequest.getMobileNumber());

		bankAccount.setBalance(bankAccount.getBalance() - plan.getPrice());
		Set<Plan> plans = user.getPlans();
		plans.add(plan);
		user.setPlans(plans);
		userRepository.save(user);
		bankAccountRepository.save(bankAccount);
		transactionRepository.save(transaction);

		return convertTransactionToTrasactionResponse(transaction);

	}

	TransactionResponse convertTransactionToTrasactionResponse(Transaction transaction) {

		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setAccountId(transaction.getAccountId().getAccountId());
		transactionResponse.setBankName(transaction.getAccountId().getBankName());
		transactionResponse.setCreatedAt(transaction.getCreatedAt());
		transactionResponse.setAmount(transaction.getPlanId().getPrice());
		transactionResponse.setBalance(transaction.getAccountId().getBalance());
		transactionResponse.setModOfPayment(transaction.getModOfPayment());
		transactionResponse.setId(transaction.getId());
		transactionResponse.setPlanId(transaction.getPlanId());
		transactionResponse.setUsername(transaction.getUserId().getUsername());
		transactionResponse.setUserId(transaction.getUserId().getUserId());
		transactionResponse.setMobileNumber(transaction.getMobileNumber());
		transactionResponse.setAmountAfterDiscount(transaction.getAmount());


		return transactionResponse;

	}
}
