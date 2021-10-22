package com.example.MobRecharge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.MobRecharge.entity.Offer;
import com.example.MobRecharge.exceptions.InvalidArguementsException;
import com.example.MobRecharge.exceptions.ResourceNotFoundException;
import com.example.MobRecharge.service.OfferService;

@RestController
@RequestMapping("/mobrecharge/v1")
public class OfferController {
	@Autowired
	OfferService offerService;

	@GetMapping("/offer")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	ResponseEntity<List<Offer>> getAllOffers() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(offerService.getOffers());
		} catch (ResourceNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer Not Found", exception);
		} catch (InvalidArguementsException exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguments", exception);
		}
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/offer")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<String> addNewOffer(@Valid @RequestBody Offer offer) {
		try {
			Integer id = offerService.saveOffer(offer);
			return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added offer with id : " + id);
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguments", exc);
		}

	}

	@GetMapping("/offer/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	ResponseEntity<Offer> getOffer(@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(offerService.getOffer(id));
		} catch (ResourceNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer Not Found", exception);
		} catch (InvalidArguementsException exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguments", exception);
		}
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/offer/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<String> deleteOffer(@PathVariable Integer id) {
		try {
			offerService.deleteOffer(id);
			return ResponseEntity.status(HttpStatus.OK).body("Successfully added offer with id : " + id);
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguments", exc);
		}
	}

	@PutMapping("/offer/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<Offer> updateOffer(@PathVariable Integer id, @RequestBody Offer offer) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(offerService.updateOffer(id, offer));
		} catch (ResourceNotFoundException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer Not Found", exc);
		} catch (InvalidArguementsException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad arguements", exc);
		}
	}
}
