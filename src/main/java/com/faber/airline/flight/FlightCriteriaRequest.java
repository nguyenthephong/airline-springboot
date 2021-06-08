package com.faber.airline.flight;

import java.time.LocalDate;
import java.time.LocalTime;
import com.faber.airline.airport.Airport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightCriteriaRequest {
	private Integer id;
	private Airport arrAirport;
	private Airport depAirport;
	private LocalDate arrDate;
	private LocalTime arrTime;
	private LocalDate depDate;
	private LocalTime depTime;
	private double price;
	private Integer availableTickets;
}
