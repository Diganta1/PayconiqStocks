package com.payconiq.rest.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payconiq.rest.webservices.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer>{

	
}
