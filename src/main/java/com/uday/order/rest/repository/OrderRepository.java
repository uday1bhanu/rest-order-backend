package com.uday.order.rest.repository;

import org.springframework.stereotype.Repository;

import com.uday.order.rest.domain.Order;

@Repository
public class OrderRepository extends InMemoryRepository<Order> {

	@Override
	public void updateIfExists(Order original, Order desired) {
		if(original != null && desired != null){
			original.setDescription(desired.getDescription());
			original.setCostInCents(desired.getCostInCents());
			original.setIsComplete(desired.getIsComplete());
			
		}
		
	}

}
