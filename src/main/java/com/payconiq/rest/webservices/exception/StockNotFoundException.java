package com.payconiq.rest.webservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Stock_not_found exception
 * @author diganta
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public StockNotFoundException(String message) {
		super(message);
	}
}
