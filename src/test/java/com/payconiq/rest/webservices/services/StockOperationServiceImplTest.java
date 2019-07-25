package com.payconiq.rest.webservices.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.payconiq.rest.webservices.exception.StockNotFoundException;
import com.payconiq.rest.webservices.model.Price;
import com.payconiq.rest.webservices.model.Stock;
import com.payconiq.rest.webservices.model.StockResponse;
import com.payconiq.rest.webservices.repository.PriceRepository;
import com.payconiq.rest.webservices.repository.StockRepository;

@RunWith(MockitoJUnitRunner.class)
public class StockOperationServiceImplTest {
	
	@InjectMocks
	private StockOperationsServiceImpl testStockOperationServiceImpl;
	
	@Mock
	private StockRepository stockRepository;
	
	@Mock
	private PriceRepository priceRepository;

	@Test
	public void testFindStock() {
		Stock stock = new Stock(1, "testStock");
		when(stockRepository.findById(1)).thenReturn(Optional.of(stock));
		StockResponse stockResponse = testStockOperationServiceImpl.findStock(1);
		assertEquals("Check Stock Name", "testStock", stockResponse.getName());
		assertEquals("Check Stock ID", new BigDecimal(1), new BigDecimal(stockResponse.getId()));	
		verify(stockRepository).findById(1);
		verifyNoMoreInteractions(stockRepository);
	}
	
	@Test(expected = StockNotFoundException.class)
	public void testFindStockNotFoundException() {
			testStockOperationServiceImpl.findStock(1);	
	}
	
	@Test
	public void testAllFindStocks() {
		Stock stock = new Stock(1, "testStock");
		List<Stock> responses = new ArrayList<Stock>();
		responses.add(stock);
		when(stockRepository.findAll()).thenReturn(responses);
		List<StockResponse> stockResponse = testStockOperationServiceImpl.findAllStocks();
		assertEquals("Size of the list of responbse", stockResponse.size(), 1);
		assertEquals("Check name of the stock", "testStock", stockResponse.get(0).getName());
		assertEquals("Check Stock ID", new BigDecimal(1), new BigDecimal(stockResponse.get(0).getId()));	
		verify(stockRepository).findAll();
		verifyNoMoreInteractions(stockRepository);
	}
	
	@Test
	public void testaddStock() {
		Stock stock = new Stock(1, "testStock");
		Price price = new Price(1, 22.0, null);
		List<Price> priceList = new ArrayList<Price>();
		priceList.add(price);	
		stock.setPrices(priceList);
		when(stockRepository.save(stock)).thenReturn((stock));
		Stock stockResponse = stockRepository.save(stock);
		assertEquals("Check Stock Name", "testStock", stockResponse.getName());
		assertEquals("Check Stock ID", new BigDecimal(1), new BigDecimal(stockResponse.getId()));	
		assertEquals("List size of the price", stockResponse.getPrices().size(), 1);
		assertEquals("Price details", stockResponse.getPrices().get(0).getprices(), new Double(22.0));
		assertEquals("Price Id", new BigDecimal(1), new BigDecimal(stockResponse.getPrices().get(0).getId()));
		verify(stockRepository).save(stock);
		verifyNoMoreInteractions(stockRepository);
	}

	@Test
	public void testUpdateStockPrice() {
		Stock stock = new Stock(1, "testStock");
		DateTimeUtils.setCurrentMillisFixed(10L);
		Price price = new Price(null, 22.0, new Timestamp(10L));
		price.setStock(stock);
		when(stockRepository.findById(1)).thenReturn(Optional.of(stock));
		testStockOperationServiceImpl.updateStockPrice(1, 22.0);
		verify(stockRepository).findById(1);
		verifyNoMoreInteractions(stockRepository);
	}
	
	@Test(expected = StockNotFoundException.class)
	public void testUpdateStockPriceWithStockNotFoundException() {
		Stock stock = new Stock(1, "testStock");
		DateTimeUtils.setCurrentMillisFixed(10L);
		Price price = new Price(null, 22.0, new Timestamp(10L));
		price.setStock(stock);
		testStockOperationServiceImpl.updateStockPrice(1, 22.0);
	}
}
