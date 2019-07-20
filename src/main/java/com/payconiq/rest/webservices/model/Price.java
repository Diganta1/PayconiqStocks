package com.payconiq.rest.webservices.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Price {
	
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private Double price;
	private Timestamp timestamp;
	
	@ManyToOne
	@JsonIgnore
	private Stock stock;
	
	/*
	 * @OneToMany(mappedBy="price") private List<TimeUpdate> times;
	 */
	
	public Price() {

	}
	
	public Price(Integer id, Double price, Timestamp timestamp) {
		super();
		this.id = id;
		this.price = price;
		this.timestamp=timestamp;
		
	}
	

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getprice() {
		return price;
	}

	public void setPrice(Double price) {
		
		this.price = price;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return String.format("Price [id=%s, price=%s , timestamp=%s]", id, price, timestamp);
	}
	
}