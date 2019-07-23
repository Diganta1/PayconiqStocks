package com.payconiq.rest.webservices.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.rest.webservices.exception.StockNotFoundException;
import com.payconiq.rest.webservices.model.PriceRequest;
import com.payconiq.rest.webservices.model.Stock;
import com.payconiq.rest.webservices.model.StockLists;
import com.payconiq.rest.webservices.model.StockResponse;
import com.payconiq.rest.webservices.services.StockOperationsService;

/**
 * Controller method is used to get the rest resource for stock object
 *
 */
@RestController
public class StockController {//

	@Autowired
	private StockOperationsService stockOperationsService;

	/**
	 * @return all Stock latest result
	 */
	@GetMapping("/api/stocks")
	public ResponseEntity<StockLists> getAllStocks() {
		return ResponseEntity.ok(new StockLists(stockOperationsService.findAllStocks()));
	}

	/**
	 * @param id Stock Id Based search
	 * @return Result fetched with specific ID
	 * @throws StockNotFoundException Stock ID not present in the database
	 */
	@GetMapping("/api/stocks/{id}")
	public ResponseEntity<StockResponse> getStockById(@PathVariable int id) throws StockNotFoundException {
		return ResponseEntity.ok(stockOperationsService.findStock(id));
	}

	/**
	 * @param stock Take stock object in body
	 * @return response body with HTTP status
	 */

	@PostMapping("/api/stocks")
	public ResponseEntity<Stock> addStock(@Valid @RequestBody Stock stock) {
		return ResponseEntity.ok(stockOperationsService.addStock(stock));
	}

	/**
	 * @param stockId take stock ID
	 * @param price   price body
	 * @return response message with HTTP Status
	 * @throws StockNotFoundException
	 * @throws JsonProcessingException
	 */
	@PutMapping("/api/stocks/{stockid}")
	public ResponseEntity<String> updateStockPrice(@Valid @PathVariable(name = "stockid") int stockId,
			@RequestBody PriceRequest price) throws StockNotFoundException, JsonProcessingException {
		stockOperationsService.updateStockPrice(stockId, price.getPrice());
		return new ResponseEntity<String>(new ObjectMapper().writerWithDefaultPrettyPrinter()
				.writeValueAsString(String.format("Stock %s is updated successfully", stockId)), HttpStatus.OK);
	}
}