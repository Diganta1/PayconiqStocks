package com.payconiq.rest.webservices.services;

import java.util.List;
import java.util.Optional;

import com.payconiq.rest.webservices.model.Stock;
import com.payconiq.rest.webservices.model.StockResponse;

public interface StockOperationsService {
	
	public <T> StockResponse findStock(int stockId);

	public List<StockResponse> findAllStocks();

	public int addStock(Stock stock);

	public void updateStockPrice(int stockId, double price);
}
