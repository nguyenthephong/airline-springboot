package com.faber.airline.airport;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;

@RestController
public class AirportResource {
	
	@Autowired
	private AirportRepository airportRepository;
	
	@CrossOrigin
	@ApiOperation("Get all airports")
	@RequestMapping(value = "/airports", method = RequestMethod.GET)
	public List<Airport> getAllAirports() {
		return airportRepository.findAll();
	}
	
	@SneakyThrows
	@CrossOrigin
	@ApiOperation("Get a airport by id")
	@RequestMapping(value = "/airports/{airportId}", method = RequestMethod.GET)
	public Airport getAirportById(@PathVariable("airportId") Integer airportId) {
		Optional<Airport> airport = airportRepository.findById(airportId);
		if (!airport.isPresent())
			throw new AirportNotFoundException("id-" + airportId);
		
		return airport.get();
	}
}
