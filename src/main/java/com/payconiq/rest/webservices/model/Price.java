package com.payconiq.rest.webservices.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Price model
 * @author diganta
 *
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Price {

	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private Double prices;
	private Timestamp timestamp;

	@ManyToOne
	@JsonIgnore
	private Stock stock;

	

	public Price() {

	}

	
	public Price(Integer id, Double prices, Timestamp timestamp) {
		super();
		this.id = id;
		this.prices = prices;
		this.timestamp = timestamp;

	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {

		this.timestamp = timestamp;
	}

	@JsonIgnore
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getprices() {
		return prices;
	}

	public void setPrices(Double prices) {

		this.prices = prices;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return String.format("Price [id=%s, price=%s , timestamp=%s]", id, prices, timestamp);
	}

}