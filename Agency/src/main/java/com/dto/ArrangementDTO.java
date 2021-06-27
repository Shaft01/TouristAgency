package com.dto;

import java.util.Date;

import com.model.Arrangement;

public class ArrangementDTO {
	private Long id;
	private Date startDate;
	private Date endDate;
	private Long userId;
	private Long hotelRoomId;
	private String details;
	private Double price;
	private String roomType;
	
	public ArrangementDTO(Arrangement source) {
		this.id = source.getId();
		this.startDate = source.getStartDate();
		this.endDate = source.getEndDate();
		this.hotelRoomId = source.getHotelRoom().getId();
		this.price = source.getPrice();
		this.userId = source.getUser().getId();
		this.details = source.getDetails();
		this.setRoomType(source.getHotelRoom().getRoom().getType());
	}
	public ArrangementDTO() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getHotelRoomId() {
		return hotelRoomId;
	}
	public void setHotelRoomId(Long hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
}
