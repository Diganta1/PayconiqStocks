package com.payconiq.rest.webservices.model;

public class StockResponse {

	private final Integer id;

	private final String name;

	private final Price latestPrice;

	public StockResponse(Integer id, String name, Price latestPrice) {
		super();
		this.id = id;
		this.name = name;
		this.latestPrice = latestPrice;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Price getLatestPrice() {
		return latestPrice;
	}

	@Override
	public String toString() {
		return String.format("Stock [id=%s, name=%s]", id, name);
	}

}