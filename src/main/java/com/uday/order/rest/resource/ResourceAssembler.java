package com.uday.order.rest.resource;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class ResourceAssembler<DomainType, ResourceType> {

	public abstract ResourceType toResource(DomainType domainType);
	
	public abstract DomainType toDomain(ResourceType resourceType);
	
	public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainTypes){
		return domainTypes.stream().map(domainType -> toResource(domainType)).collect(Collectors.toList());
	}
	
	public Collection<DomainType> toDomainTypeCollection(Collection<ResourceType> resourceTypes){
		return resourceTypes.stream().map(resourceType -> toDomain(resourceType)).collect(Collectors.toList());
	}

}
