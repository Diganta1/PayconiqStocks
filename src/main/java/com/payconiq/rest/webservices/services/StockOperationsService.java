package com.payconiq.rest.webservices.services;

import java.util.List;

import com.payconiq.rest.webservices.model.Stock;
import com.payconiq.rest.webservices.model.StockResponse;

/**
 *  Interface for service call
 * @author diganta
 *
 */
public interface StockOperationsService {
	
	public StockResponse findStock(int stockId);

	public List<StockResponse> findAllStocks();

	public Stock addStock(Stock stock);

	public void updateStockPrice(int stockId, double price);
}
