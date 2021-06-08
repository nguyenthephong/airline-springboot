package com.faber.airline.order;

import java.time.LocalDateTime;

import com.faber.airline.flight.Flight;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCriteriaRequest {
	private Integer id;
	private LocalDateTime timeOfOrder;
	private String returnType;
	private Flight flight;
	private int totalPeople;
	private double totalPrice;
}
