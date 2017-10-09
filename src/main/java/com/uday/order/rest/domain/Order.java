package com.uday.order.rest.domain;

public class Order implements Identifiable {
	private Long id;
	private String description;
	private Long costInCents;
	private Boolean isComplete;
	
	public Order() {
		
	}

	public Order(Long id, String description, Long costInCents, Boolean isComplete) {
		super();
		this.id = id;
		this.description = description;
		this.costInCents = costInCents;
		this.isComplete = isComplete;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCostInCents() {
		return costInCents;
	}

	public void setCostInCents(Long costInCents) {
		this.costInCents = costInCents;
	}

	public Boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public void markComplete() {
		setIsComplete(true);
	}
	
	public void markIncomplete() {
		setIsComplete(false);
	}

}
