package com.example.MobRecharge.dto;

public class PlanRequest {
	private float price;
	
	private int validity;
	
	private int sms;
	
	private int callMinutes;
	
	private float data;
	
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
