/**
 * 
 */
package com.payconiq.rest.webservices.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
 * Implementation class for Stock operation service 
 * @author diganta
 *
 */
@Service
public class StockOperationsServiceImpl implements StockOperationsService {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StockOperationsServiceImpl.class);
	private static final String INFO_FETCHALL="searched id(S):{} and name :{} from db";
	private static final String INFO_ADD="Saved Item id:{} and name :{} into db";
	
	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private PriceRepository priceRepository;

	/**
	 *find all stocks wrt IDs
	 */
	@Override
	public StockResponse findStock(int stockId) {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new StockNotFoundException(String.format("Stock %s does not exists", stockId)));
		return new StockResponse(stock.getId(), stock.getName(), stock.getLatestPrice());
	}

	/**
	 *Find all stocks
	 */
	@Override
	public List<StockResponse> findAllStocks() {
		List<StockResponse> stockResponse = new ArrayList<>();
		stockRepository.findAll().forEach(stock -> {
			stockResponse.add(new StockResponse(stock.getId(), stock.getName(), stock.getLatestPrice()));
			LOGGER.info(INFO_FETCHALL,stock.getId(),stock.getName());
		});
		
		return stockResponse;
	}

	/**
	 *register new stock
	 */
	@Override
	public Stock addStock(Stock stock) {
		for (Price price : stock.getPrices()) {
			price.setTimestamp(new Timestamp(new DateTime().getMillis()));
			price.setStock(stock);
		}
		Stock savedStock = stockRepository.save(stock);
		LOGGER.info(INFO_ADD,savedStock.getId(),savedStock.getName());
		return savedStock;
	}

	/**
	 *add latest price to the stock
	 */
	@Override
	public void updateStockPrice(int stockId, double newPrice) {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new StockNotFoundException(String.format("Stock %s does not exists", stockId)));
		Price price = new Price();
		price.setPrices(newPrice);
		price.setTimestamp(new Timestamp(new DateTime().getMillis()));
		price.setStock(stock);
		priceRepository.save(price);
	}
}