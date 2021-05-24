package com.dto;

import com.model.HotelRoom;

public class HotelRoomDTO {

	private Long id;
	private Long hotelId;
	private Long roomId;
	private Double pricePerPerson;
	private String hotelName;
	private String roomType;
	
	public HotelRoomDTO() {
		
	}
	public HotelRoomDTO(HotelRoom source) {
		this.id = source.getId();
		this.hotelId = source.getHotel().getId();
		this.roomId = source.getRoom().getId();
		this.pricePerPerson = source.getPricePerPerson();
		this.hotelName = source.getHotel().getName();
		this.roomType = source.getRoom().getType();
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
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Double getPricePerPerson() {
		return pricePerPerson;
	}
	public void setPricePerPerson(Double pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}
}
