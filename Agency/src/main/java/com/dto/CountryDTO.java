package com.dto;

import com.model.Country;

public class CountryDTO {
	private Long id;
	private String name;
	
	public CountryDTO(){

	}
	
	public CountryDTO(Country source) {
		super();
		this.id = source.getId();
		this.name = source.getName();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
