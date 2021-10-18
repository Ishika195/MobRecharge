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
		offerRepository.save(offer);
		return offer.getOfferId();
	}

	public Offer getOffer(Integer id) {
		return offerRepository.findByOfferId(id);
	}

	public void deleteOffer(Integer id) {
		offerRepository.deleteById(id);
	}
}
