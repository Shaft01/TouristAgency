package com.dto;

import com.model.City;

public class CityDTO {
	private Long id;
	private String name;
	private Long countryId;
	
	public CityDTO() {
		
	}
	public CityDTO(City source) {
		this.setId(source.getId());
		this.setName(source.getName());
		this.setCountryId(source.getCountry().getId());
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
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
}
