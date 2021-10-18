package com.example.MobRecharge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.repository.OfferRepository;
import com.example.MobRecharge.entity.Offer;

@Service
public class OfferService {
	@Autowired
	OfferRepository offerRepository;

	public List<Offer> getOffers() {
		return offerRepository.findAll();
	}

	public Integer saveOffer(Offer offer) {
		if (offer == null) {
			throw new InvalidArguementsException("Empty Object");
		}
		offerRepository.save(offer);
		return offer.getOfferId();
	}

	public Offer getOffer(Integer id) {
		if (id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}

		Offer offer;
		try {
			offer = offerRepository.findByOfferId(id);
		} catch (RuntimeException e) {
			throw new ResourceNotFoundException("offer not found");
		}
		return offer;
	}

	public void deleteOffer(Integer id) {
		if (id <= 0) {
			throw new InvalidArguementsException("Invalid Id");
		}
		offerRepository.deleteById(id);
	}

	public Offer updateOffer(Integer id, Offer offer) {
		if (id == 0 || offer == null) {
			throw new RuntimeException("Offer does not exist");
		}
		Offer existingOffer = offerRepository.findByOfferId(id);
		if (existingOffer == null) {
			throw new ResourceNotFoundException("offer not found");
		}
		if (offer.getDiscount() != 0) {
			existingOffer.setDiscount(offer.getDiscount());
		}
		if (offer.getMinAmount() != 0) {
			existingOffer.setMinAmount(offer.getMinAmount());
		}
		if (offer.getMaxAmount() != 0) {
			existingOffer.setMinAmount(offer.getMaxAmount());
		}
		offerRepository.save(existingOffer);
		return existingOffer;
	}
}
