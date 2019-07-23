package com.payconiq.rest.webservices.model;

import java.util.ArrayList;
import java.util.List;

public class StockLists {

	private List<StockResponse> listOfStock;

	public StockLists(List<StockResponse> listOfStock) {
		this.listOfStock = listOfStock;
	}

	public List<StockResponse> getListOfStock() {
		if (null == listOfStock) {
			this.listOfStock  = new ArrayList<>();
		}
		return listOfStock;
	}

}
