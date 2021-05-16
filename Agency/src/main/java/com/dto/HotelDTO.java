package com.dto;

import com.model.Hotel;

public class HotelDTO {
	private Long id;
	private String name;
	private Long cityId;
	private String cityName;
	
	public HotelDTO() {
		
	}
	public HotelDTO(Hotel source) {
		this.id = source.getId();
		this.name = source.getName();
		this.cityId = source.getCity().getId();
		this.setCityName(source.getCity().getName());
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
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
