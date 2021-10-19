package com.example.MobRecharge.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Plan {
	
<<<<<<< Updated upstream
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	
=======
>>>>>>> Stashed changes
	private float price;
	
	private int validity;
	
	private int sms;
	
	private int callMinutes;
	
	private float data;
	
	@CreationTimestamp
	private Date createdAt;
	
	@UpdateTimestamp
	private Date updatedAt;
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

<<<<<<< Updated upstream
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
=======
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int planId;
	
	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
>>>>>>> Stashed changes
	}

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
