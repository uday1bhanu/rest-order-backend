package com.uday.order.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uday.order.rest.domain.Order;

public class OrderResource extends ResourceSupport{
	@JsonInclude(Include.NON_NULL)
	private final Long id;
	private final String description;
	private final Long costInCents;
	private final Boolean complete;
	
	@JsonCreator
	public OrderResource(Order order) {
		super();
		this.id = order.getId();
		this.description = order.getDescription();
		this.costInCents = order.getCostInCents();
		this.complete = order.getIsComplete();
	}
	
	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public Long getCostInCents() {
		return costInCents;
	}
	public Boolean getComplete() {
		return complete;
	}
}
