package com.faber.airline.order;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;

@CrossOrigin(maxAge = 3600)
@RestController
public class OrderResource {

	@Autowired
	private OrderRepository orderRepository;
	
	@ApiOperation("Get all orders")
	@GetMapping(value = "/orders")
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@SneakyThrows
	@ApiOperation("Get a order by id")
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public Order getOrderById(@PathVariable("orderId") Integer orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (!order.isPresent())
			throw new OrderNotFoundException("id-" + orderId);
		
		return order.get();
	}
	
	@SneakyThrows
	@ApiOperation("Create a new order")
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public Order createOrder(@RequestBody OrderCriteriaRequest orderRequest) {
		try {
			Order order = new Order();
			order.setTimeOfOrder(orderRequest.getTimeOfOrder());
			order.setReturnType(orderRequest.getReturnType());
			order.setFlight(orderRequest.getFlight());
			order.setTotalPeople(orderRequest.getTotalPeople());
			order.setTotalPrice(orderRequest.getTotalPrice());
			Order resOrder = orderRepository.save(order);
			return resOrder;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@SneakyThrows
	@ApiOperation("Update a new order")
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.PUT)
	public void updateFlight(@RequestBody OrderCriteriaRequest orderRequest, @PathVariable("orderId") Integer orderId) {	
		try {
			Optional<Order> opt = orderRepository.findById(orderId);
			if (!opt.isPresent())
				throw new OrderNotFoundException("id-" + orderId);
			
			Order existingOrder = opt.get();
			existingOrder.setFlight(orderRequest.getFlight());
			existingOrder.setReturnType(orderRequest.getReturnType());
			existingOrder.setTimeOfOrder(orderRequest.getTimeOfOrder());
			existingOrder.setTotalPeople(orderRequest.getTotalPeople());
			existingOrder.setTotalPrice(orderRequest.getTotalPrice());
			orderRepository.save(existingOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SneakyThrows
	@ApiOperation("Delete a order")
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.DELETE)
	public void deleteFlight(@PathVariable("orderId") Integer orderId) {
		try {
			orderRepository.deleteById(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}