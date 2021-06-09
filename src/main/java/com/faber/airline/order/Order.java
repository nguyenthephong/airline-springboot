package com.faber.airline.order;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.faber.airline.flight.Flight;

@ApiModel(description="All details about the Order.")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderFlight")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "timeOfOrder")
	private LocalDateTime timeOfOrder;
	
	@Column(name = "returnType")
	private String returnType;

	@ManyToOne
	@JoinColumn(name = "flightId")
	private Flight flight;
	
	@Column(name = "totalPeople")
	private int totalPeople;
	
	@Column(name = "totalPrice")
	private double totalPrice;

}
