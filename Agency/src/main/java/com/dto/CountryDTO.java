package com.dto;

import com.model.Country;

public class CountryDTO {
	private Long id;
	private String name;
	private String imagePath;
	public CountryDTO(){

	}
	
	public CountryDTO(Country source) {
		super();
		this.id = source.getId();
		this.name = source.getName();
		this.imagePath=source.getImagePath();
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
