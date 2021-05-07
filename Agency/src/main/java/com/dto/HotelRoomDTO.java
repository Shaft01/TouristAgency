package com.dto;

import com.model.HotelRoom;

public class HotelRoomDTO {

	private Long id;
	private Long hotelId;
	private Long roomId;
	private Double pricePerDay;
	
	public HotelRoomDTO() {
		
	}
	public HotelRoomDTO(HotelRoom source) {
		this.id = source.getId();
		this.hotelId = source.getHotel().getId();
		this.roomId = source.getRoom().getId();
		this.pricePerDay = source.getPricePerDay();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Double getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
}
