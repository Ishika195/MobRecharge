package com.example.MobRecharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.MobRecharge.dto.PlanRequest;
import com.example.MobRecharge.entity.Plan;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.service.PlanService;

@RestController
@RequestMapping("/mobrecharge/v1/")
public class PlanController {
	@Autowired
	PlanService planService;

	@GetMapping("plan")
	ResponseEntity<List<Plan>> getPlans() {
		List<Plan> plans = planService.getAllPlans();
		return ResponseEntity.status(HttpStatus.OK).body(plans);

	}

	@GetMapping("plan/{id}")
	ResponseEntity<Plan> getPlan(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(planService.getPlan(id));

		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plans Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@PostMapping("plan")
	ResponseEntity<String> createPlan(@RequestBody PlanRequest planRequest) {
		try {
			planService.createPlan(planRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body("Plan Saved");
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@DeleteMapping("plan/{id}")
	ResponseEntity<String> createPlan(@PathVariable int id) {
		try {
			planService.deletePlan(id);
			return ResponseEntity.status(HttpStatus.OK).body("Plan Deleted");
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		} catch (IllegalArgumentException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

	@PutMapping("plan/{id}")
	ResponseEntity<Plan> updatePlan(@PathVariable int id, @RequestBody Plan plan) {
		System.out.println(plan);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(planService.updatePlan(id, plan));

		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plans Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}

}
