package com.dto;

import com.model.City;

public class CityDTO {
	private Long id;
	private String name;
	private Long countryId;
	private String countryName;
	private String imagePath;
	private String description;
	public CityDTO() {
		
	}
	public CityDTO(City source) {
		this.setId(source.getId());
		this.setName(source.getName());
		this.setCountryId(source.getCountry().getId());
		this.setCountryName(source.getCountry().getName());
		this.setImagePath(source.getImagePath());
		this.setDescription(source.getDescription());
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
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
