package com.example.MobRecharge.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class BankAccountRequest {
	@NotBlank(message = "Bank Name is mandatory")
	private String bankName;

	@NotBlank(message = "Account holder Name is mandatory")
	private String holderName;

	@NotNull
	@Positive
	private int Number;

	@NotNull
	@PositiveOrZero
	private float balance;

	@NotNull
	private Long userId;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
