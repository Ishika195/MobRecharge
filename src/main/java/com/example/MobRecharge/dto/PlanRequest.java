package com.example.MobRecharge.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PlanRequest {
	@NotNull
	@Positive
	private float price;
	
	@NotNull
	@Positive
	private int validity;
	
	@NotNull
	@Positive
	private int sms;
	
	@NotNull
	@Positive
	private int callMinutes;
	
	@NotNull
	@Positive
	private float data;
	
	@NotNull
	@NotNull
	@Positive
	private int offerId;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public int getSms() {
		return sms;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}

	public int getCallMinutes() {
		return callMinutes;
	}

	public void setCallMinutes(int callMinutes) {
		this.callMinutes = callMinutes;
	}

	public float getData() {
		return data;
	}

	public void setData(float data) {
		this.data = data;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	
}
