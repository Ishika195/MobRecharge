package com.example.MobRecharge.dto;

import com.example.MobRecharge.entity.ModOfPayment;

public class TransactionRequest {

	private Integer id;
	private ModOfPayment modOfPayment;
	
	private Long userId;
	
	private int planId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModOfPayment getModOfPayment() {
		return modOfPayment;
	}

	public void setModOfPayment(ModOfPayment modOfPayment) {
		this.modOfPayment = modOfPayment;
	}



	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	private int accountNumber;
}
