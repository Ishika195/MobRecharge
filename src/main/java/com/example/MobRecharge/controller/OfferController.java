package com.example.MobRecharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.MobRecharge.entity.Offer;
import com.example.MobRecharge.service.OfferService;

@RestController
@RequestMapping("/MobRechargeApp/v1")
public class OfferController {
	@Autowired
	OfferService offerService;

	@GetMapping("/offers")
	List<Offer> getAllOffers() {
		return offerService.getOffers();
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/offer")
	String addNewOffer(@RequestBody Offer offer) {
		Integer id = offerService.saveOffer(offer);
		return "Successfully added offer with id : " + id;
	}

	@GetMapping("/offers/{id}")
	Offer getOffer(@PathVariable Integer id) {
		return offerService.getOffer(id);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/offer/{id}")
	String deleteOffer(@PathVariable Integer id) {
		offerService.deleteOffer(id);
		return "Successfully added offer with id : " + id;
	}

	@PutMapping("/offer/{id}")
	Offer updateOffer(@PathVariable Integer id, @RequestBody Offer offer) {
		offer.setOfferId(id);
		return offerService.updateOffer(id, offer);
	}
}
