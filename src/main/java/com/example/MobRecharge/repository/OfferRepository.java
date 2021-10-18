package com.example.MobRecharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MobRecharge.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {

	Offer findByOfferId(Integer id);

	//void deleteByOfferId(Integer id);

}
