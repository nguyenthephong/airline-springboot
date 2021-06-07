package com.faber.airline.flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;

@RestController
public class FlightResource {

	@Autowired
	private FlightRepository flightRepository;
	
	@CrossOrigin
	@ApiOperation("Get all flights")
	@RequestMapping(value = "/flights", method = RequestMethod.GET)
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}
	
	@CrossOrigin
	@ApiOperation("Get all flights")
	@RequestMapping(value = "/flights/search", method = RequestMethod.GET)
	public List<Flight> getAllFlightsWithConditions(
			@RequestParam(value = "from", required = false) Integer depAirportId,
			@RequestParam(value = "to", required = false) Integer arrAirportId,
			@RequestParam(value = "depDate", required = false) String depDate,
			@RequestParam(value = "arrDate", required = false) String arrDate,
			@RequestParam(value = "totalTickets", required = false) Integer totalTickets) {

		FlightSpecificationsBuilder builder = new FlightSpecificationsBuilder();

		if (depAirportId != null) {
			builder.with("depAirport", ":", depAirportId);
		}
		if (arrAirportId != null) {
			builder.with("arrAirport", ":", arrAirportId);
		}
		if (depDate != null) {
			builder.with("depDate", ":", LocalDate.parse(depDate));
		}
		if (arrDate != null) {
			builder.with("arrDate", ":", LocalDate.parse(arrDate));
		}
		if (totalTickets != null) {
			builder.with("availableTickets", ">", totalTickets);
		}

		List<Flight> results = flightRepository.findAll(builder.build());

		return results;
	}
	
	@CrossOrigin
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
	public Flight creatFlight(@RequestBody Flight flightRequest) {
		try {
			Flight flight = flightRepository.save(flightRequest);
			return flight;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@CrossOrigin
	@SneakyThrows
	@ApiOperation("Update a new flight")
	@RequestMapping(value = "/flights/{flightId}", method = RequestMethod.PUT)
	public void updateFlight(@RequestBody Flight flightRequest, @PathVariable("flightId") Integer flightId) {	
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
			flightRepository.save(existingFlight);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@CrossOrigin
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
