package com.example.MobRecharge.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.entity.Plan;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.repository.PlanRepository;

@Service
public class PlanService {
	@Autowired
	PlanRepository planRepository;
	
	public List<Plan> getAllPlans(){
		return planRepository.findAll();
	}
	
	public void deletePlan(int id) {
		if(id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		planRepository.deleteById(id);
	}
	
	public Plan createPlan(Plan plan) {
		if(plan==null) {
			throw new InvalidArguementsException("Empty Object");
		}
		return planRepository.save(plan);
	}
	
	public Plan getPlan(int id) {
		if(id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		
			Plan plan;
			try{
				plan = planRepository.getById(id);
			}
			catch(RuntimeException e) {
				throw new ResourceNotFoundException("plan not found");
			}

			return plan;
		
	}
	
	public Plan updatePlan(int id,Plan plan) {
		if(id==0 || plan == null) {
			throw new RuntimeException();
		}
		Plan exsistingPlan  = planRepository.getById(id);
		if(exsistingPlan==null) {
			throw new ResourceNotFoundException("plan not found");
		}
		if(plan.getData()!=0.0f) {
			exsistingPlan.setData(plan.getData());
		}
		if(plan.getValidity()!=0) {
			exsistingPlan.setValidity(plan.getValidity());
		}
		if(plan.getCallMinutes()!=0) {
			exsistingPlan.setCallMinutes(plan.getCallMinutes());
		}
		
		if(plan.getSms()!=0) {
			exsistingPlan.setSms(plan.getSms());
		}
		planRepository.save(exsistingPlan);
		return exsistingPlan;
		
	}
	
}
