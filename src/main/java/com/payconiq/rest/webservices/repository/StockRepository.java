package com.payconiq.rest.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payconiq.rest.webservices.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

}
