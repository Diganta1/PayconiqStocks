package com.payconiq.rest.webservices.controller;

import java.util.List;

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

import com.payconiq.rest.webservices.exception.StockNotFoundException;
import com.payconiq.rest.webservices.model.PriceRequest;
import com.payconiq.rest.webservices.model.Stock;
import com.payconiq.rest.webservices.model.StockResponse;
import com.payconiq.rest.webservices.model.User;
import com.payconiq.rest.webservices.services.StockOperationsService;
import com.payconiq.rest.webservices.services.UserService;

/**
 * Controller method is used to get the rest resource for stock object
 *
 */
@RestController
public class StockController {

	@Autowired
	private StockOperationsService stockOperationsService;

	@Autowired
	private UserService userService;

	@GetMapping("/api/users")
	public List<User> listUser() {
		return userService.findAll();
	}

	@GetMapping("/api/stocks")
	public List<StockResponse> getAllStocks() {
		return stockOperationsService.findAllStocks();
	}

	@GetMapping("/api/stocks/{id}")
	public StockResponse getStockById(@PathVariable int id) throws StockNotFoundException {
		return stockOperationsService.findStock(id).get();
	}

	@PostMapping("/api/stocks")
	public ResponseEntity<String> addStock(@Valid @RequestBody Stock stock)  {
		int stockId = stockOperationsService.addStock(stock);
		return new ResponseEntity<String>(String.format("Stock %s is created successfully", stockId), HttpStatus.CREATED);
	}


	@PutMapping("/api/stocks/{stockid}/prices")
	public ResponseEntity<Object> updateStockPrice(@PathVariable(name = "stockid") int stockId, @RequestBody PriceRequest price)
			throws StockNotFoundException {
		stockOperationsService.updateStockPrice(stockId, price.getPrice());
		return new ResponseEntity<Object>(String.format("Stock %s is updated successfully", stockId), HttpStatus.OK);
	}
}