package com.faber.airline.flight;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.faber.airline.airport.Airport;

@ApiModel(description="All details about the Flight.")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Flight")
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "arrAirportId", referencedColumnName = "id")
	private Airport arrAirport;

	@ManyToOne
	@JoinColumn(name = "depAirportId", referencedColumnName = "id")
	private Airport depAirport;
	
	@Column(name = "arrDate")
	private LocalDate arrDate;
	
	@Column(name = "arrTime")
	private LocalTime arrTime;
	
	@Column(name = "depDate")
	private LocalDate depDate;
	
	@Column(name = "depTime")
	private LocalTime depTime;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "avaTickets")
	private Integer availableTickets;
}
