package com.example.MobRecharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MobRecharge.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer,Integer> {

	Offer findByOfferId(Integer id);

	//void deleteByOfferId(Integer id);

}
