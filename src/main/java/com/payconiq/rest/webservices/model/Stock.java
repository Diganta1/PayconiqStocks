package com.payconiq.rest.webservices.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.JoinFormula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Stock Details", description = "Contains all details of a Stock")
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Stock {
	
	private static final String VALIDATION="Name should have atleast 3 characters";
	private static final String FORMULA="(" + "SELECT s.id " + "FROM PRICE s " + "WHERE s.STOCK_ID   = ID " + "ORDER BY s.timestamp DESC " + "LIMIT 1" + ")";

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 3, message = VALIDATION)
	@ApiModelProperty(notes = VALIDATION)
	@NotBlank(message="Stock name")
	@Column(unique = true)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "stock")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotEmpty (message="Stock amount")
	private List<Price> prices;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinFormula(FORMULA)
	@JsonIgnore
	private Price latestPrice;

	public Price getLatestPrice() {
		return latestPrice;
	}

	public void setLatestPrice(Price latestPrice) {
		this.latestPrice = latestPrice;
	}

	public Stock() {

	}

	public Stock(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Price> getPrices() {
		if (null == prices) {
			prices = new ArrayList<Price>();
		}
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		return String.format("Stock [id=%s, name=%s]", id, name);
	}

}