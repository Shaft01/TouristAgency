package com.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.core.io.InputStreamResource;

import com.model.Country;

public class CountryDTO {
	private Long id;
	private String name;
	private String imagePath;
	private String description;
	
	
	public CountryDTO(){

	}
	
	public CountryDTO(Country source) {
		super();
		this.id = source.getId();
		this.name = source.getName();
		this.imagePath=source.getImagePath();
		this.description = source.getDescription();
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
