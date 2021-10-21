package com.example.MobRecharge.dto;

import java.util.Date;

import com.example.MobRecharge.entity.ModOfPayment;
import com.example.MobRecharge.entity.Plan;
import com.example.MobRecharge.entity.User;

public class TransactionResponse {

	private Integer id;
	private float amount;
	private ModOfPayment modOfPayment;
	private User userId;	
	private Plan planId;
	private String username;
	private float balance;
	private int accountId;
	private String bankName;
	private float amountAfterDiscount;
	private Long mobileNumber;

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public ModOfPayment getModOfPayment() {
		return modOfPayment;
	}
	public void setModOfPayment(ModOfPayment modOfPayment) {
		this.modOfPayment = modOfPayment;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Plan getPlanId() {
		return planId;
	}
	public void setPlanId(Plan planId) {
		this.planId = planId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}

	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public float getAmountAfterDiscount() {
		return amountAfterDiscount;
	}

	public void setAmountAfterDiscount(float amountAfterDiscount) {
		this.amountAfterDiscount = amountAfterDiscount;
	}
	private Date createdAt;
}
