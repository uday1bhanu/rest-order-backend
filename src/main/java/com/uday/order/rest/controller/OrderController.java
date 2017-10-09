package com.uday.order.rest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uday.order.rest.domain.Order;
import com.uday.order.rest.repository.OrderRepository;
import com.uday.order.rest.resource.OrderResource;
import com.uday.order.rest.resource.OrderResourceAssembler;

@RestController
@ExposesResourceFor(Order.class)
@RequestMapping(value = "/orders", produces = "application/json")
public class OrderController {

	@Autowired
	public OrderRepository orderRepository; 
	
	@Autowired
	public OrderResourceAssembler orderResourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<OrderResource>> findAllOrders(){
		return new ResponseEntity<Collection<OrderResource>>(orderResourceAssembler.toResourceCollection(orderRepository.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OrderResource> createOrder(@RequestBody OrderResource orderResource){
		return new ResponseEntity<OrderResource>(orderResourceAssembler.toResource(orderRepository.create(orderResourceAssembler.toDomain(orderResource))), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<OrderResource> findById(@PathVariable Long id){
		try {
			return new ResponseEntity<OrderResource>(orderResourceAssembler.toResource(orderRepository.findById(id)), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		Boolean wasDeleted = orderRepository.delete(id);
		HttpStatus status = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<>(status);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<OrderResource> updateById(@PathVariable Long id, @RequestBody OrderResource body){
		try {
			Boolean wasUpdated = orderRepository.update(id, orderResourceAssembler.toDomain(body));
			if(wasUpdated){
				return findById(id);
			}
			else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
