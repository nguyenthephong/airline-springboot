package com.faber.airline.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends Exception {
	public FlightNotFoundException(String message) {
		super(message);
	}
}
