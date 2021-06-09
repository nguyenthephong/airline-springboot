package com.faber.airline.flight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;

@CrossOrigin(maxAge = 3600)
@RestController
public class FlightResource {

	@Autowired
	private FlightRepository flightRepository;
	
	@ApiOperation("Get all flights")
	@RequestMapping(value = "/flights", method = RequestMethod.GET)
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}
	
	@SneakyThrows
	@ApiOperation("Get all flights")
	@RequestMapping(value = "/flights/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Flight> getAllFlightsWithConditions(
			@RequestParam(value = "fromStation", required = false) Integer depAirportId,
			@RequestParam(value = "toStation", required = false) Integer arrAirportId,
			@RequestParam(value = "fromDate", required = false) String depDate,
			@RequestParam(value = "toDate", required = false) String arrDate,
			@RequestParam(value = "totalTickets", required = false) Integer totalTickets) {
		
		FlightSpecificationsBuilder builder = new FlightSpecificationsBuilder();

		if (depAirportId != null) {
			builder.with("depAirport", ":", depAirportId);
		}
		if (arrAirportId != null) {
			builder.with("arrAirport", ":", arrAirportId);
		}
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		if (depDate != null && !depDate.equalsIgnoreCase("undefined")) {
			builder.with("depDate", ":", LocalDate.parse(depDate, df));
		}
		if (arrDate != null && !arrDate.equalsIgnoreCase("undefined")) {
			builder.with("arrDate", ":", LocalDate.parse(arrDate, df));
		}
		if (totalTickets != null ) {
			builder.with("availableTickets", ">", totalTickets);
		}
		
		List<Flight> results = new ArrayList();

		results = flightRepository.findAll(builder.build());
		
		return results;
	}
	
	@SneakyThrows
	@ApiOperation("Get a flight by id")
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.GET)
	public Flight getFlightById(@PathVariable("flightId") Integer flightId) {
		Optional<Flight> flight = flightRepository.findById(flightId);
		if (!flight.isPresent())
			throw new FlightNotFoundException("id-" + flightId);
		
		return flight.get();
	}
	
	@CrossOrigin
	@SneakyThrows
	@ApiOperation("Create a new flight")
	@RequestMapping(value = "/flights", method = RequestMethod.POST)
	public Flight creatFlight(@RequestBody FlightCriteriaRequest flightRequest) {
		try {
			Flight flight = new Flight();
			flight.setArrAirport(flightRequest.getArrAirport());
			flight.setDepAirport(flightRequest.getDepAirport());
			flight.setArrDate(flightRequest.getArrDate());
			flight.setArrTime(flightRequest.getArrTime());
			flight.setDepDate(flightRequest.getDepDate());
			flight.setDepTime(flightRequest.getDepTime());
			flight.setPrice(flightRequest.getPrice());
			flight.setAvailableTickets(flightRequest.getAvailableTickets());

			Flight resFlight = flightRepository.save(flight);
			return resFlight;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@SneakyThrows
	@ApiOperation("Update a new flight")
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.PUT)
	public void updateFlight(@RequestBody FlightCriteriaRequest flightRequest, @PathVariable("flightId") Integer flightId) {	
		try {
			Optional<Flight> opt = flightRepository.findById(flightId);
			if (!opt.isPresent())
				throw new FlightNotFoundException("id-" + flightId);
			
			Flight existingFlight = opt.get();
			existingFlight.setArrAirport(flightRequest.getArrAirport());
			existingFlight.setDepAirport(flightRequest.getDepAirport());
			existingFlight.setArrDate(flightRequest.getArrDate());
			existingFlight.setArrTime(flightRequest.getArrTime());
			existingFlight.setDepDate(flightRequest.getDepDate());
			existingFlight.setDepTime(flightRequest.getDepTime());
			existingFlight.setPrice(flightRequest.getPrice());
			existingFlight.setAvailableTickets(flightRequest.getAvailableTickets());
			flightRepository.save(existingFlight);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SneakyThrows
	@ApiOperation("Delete a flight")
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.DELETE)
	public void deleteFlight(@PathVariable("flightId") Integer flightId) {
		try {
			flightRepository.deleteById(flightId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
