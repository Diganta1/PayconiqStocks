/**
 * 
 */
package com.payconiq.rest.webservices.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payconiq.rest.webservices.exception.StockNotFoundException;
import com.payconiq.rest.webservices.model.Price;
import com.payconiq.rest.webservices.model.Stock;
import com.payconiq.rest.webservices.model.StockResponse;
import com.payconiq.rest.webservices.repository.PriceRepository;
import com.payconiq.rest.webservices.repository.StockRepository;

/**
 * @author diganta
 *
 */
@Service
public class StockOperationsServiceImpl implements StockOperationsService {
	
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StockOperationsServiceImpl.class);
	
	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private PriceRepository priceRepository;

	@Override
	public <T> Optional<StockResponse> findStock(int stockId) {
		Optional<Stock> stock = stockRepository.findById(stockId);
		if (!stock.isPresent()) {
			LOGGER.error("Stock {} does not exists in the db", stockId);
			throw new StockNotFoundException(String.format("Stock %s does not exists", stockId));
		}
		return Optional.of(new StockResponse(stock.get().getId(), stock.get().getName(), stock.get().getLatestPrice()));
	}

	@Override
	public List<StockResponse> findAllStocks() {
		List<StockResponse> stockResponse = new ArrayList<StockResponse>();
	     stockRepository.findAll().forEach(stock -> {
			stockResponse.add(new StockResponse(stock.getId(), stock.getName(), stock.getLatestPrice()));
	     });
		return stockResponse;
	}

	@Override
	public int addStock(Stock stock) {
		for(Price price : stock.getPrices()) {
			price.setTimestamp(new Timestamp(new DateTime().getMillis()));
			price.setStock(stock);
		}
		Stock savedStock = stockRepository.save(stock);
		return savedStock.getId();
	}

	@Override
	public void updateStockPrice(int stockId, double newPrice) {
		Optional<Stock> stock = stockRepository.findById(stockId);
		if (!stock.isPresent()) {
			throw new StockNotFoundException(String.format("Stock %s does not exists", stockId));
		}
		Price price = new Price();
		price.setPrice(newPrice);
		price.setTimestamp(new Timestamp(new DateTime().getMillis()));
		price.setStock(stock.get());
		priceRepository.save(price);
	}
}