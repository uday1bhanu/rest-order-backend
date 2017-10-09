package com.uday.order.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.uday.order.rest.domain.Order;

@Component
public class OrderResourceAssembler extends ResourceAssembler<Order, OrderResource> {
	
	@Autowired
	protected EntityLinks entityLinks;
	
	private static final String UPDATE_REL = "update";
	private static final String DELETE_REL = "delete";
	
	@Override
	public OrderResource toResource(Order domainType) {
		OrderResource orderResource = new OrderResource(domainType);
		
		final Link selfLink = entityLinks.linkToSingleResource(domainType);
		orderResource.add(selfLink.withSelfRel());
		orderResource.add(selfLink.withRel(UPDATE_REL));
		orderResource.add(selfLink.withRel(DELETE_REL));
		
		return orderResource;
	}

	@Override
	public Order toDomain(OrderResource resourceType) {
		Order order = new Order(null, resourceType.getDescription(), resourceType.getCostInCents(), resourceType.getComplete());
		
		return order;
	}

}
