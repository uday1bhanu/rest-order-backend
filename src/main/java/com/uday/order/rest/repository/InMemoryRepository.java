package com.uday.order.rest.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.uday.order.rest.domain.Identifiable;

public abstract class InMemoryRepository<T extends Identifiable> {

	@Autowired
	private IdGenerator idGenerator;
	private List<T> elements = Collections.synchronizedList(new ArrayList<T>());
	
	public T create(T element){
		element.setId(idGenerator.getNextId());
		elements.add(element);
		
		return element;
	}
	
	public Boolean delete(Long id){
		return elements.removeIf(element -> element.getId().equals(id));
	}
	
	public List<T> findAll(){
		return elements;
	}
	
	public T findById(Long id) throws Exception{
		Optional<T> result = elements.stream().filter(element -> element.getId().equals(id)).findFirst();
		if(result.isPresent()){
			return result.get();
		}
		else{
			throw new Exception("Data not found for id: "+ id);
		}
	}
	
	public int getCount(){
		return elements.size();
	}
	
	public void clear(){
		elements.clear();
	}
	
	public Boolean update(Long id, T updated) throws Exception{
		if(updated == null){
			return false;
		}
		else{
			T element = findById(id);
			updateIfExists(element, updated);
			return true;
		}
	}
	
	public abstract void updateIfExists(T original, T desired);

}
