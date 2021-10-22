package com.example.MobRecharge.dto;



import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.MobRecharge.entity.ModOfPayment;

public class TransactionRequest {

	private ModOfPayment modOfPayment;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private int planId;

	@NotNull
	@Positive
	private int accountNumber;
	
	@Digits(integer = 10, fraction = 0)
	@Positive
	private Long mobileNumber;

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
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

}
