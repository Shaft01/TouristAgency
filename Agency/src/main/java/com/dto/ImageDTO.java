package com.dto;

import com.model.Image;

public class ImageDTO {
	private Long id;
	private String path;
	private Long hotelRoomId;
	public ImageDTO() {}
	public ImageDTO(Image source) {
		this.id = source.getId();
		this.path = source.getPath();
		this.hotelRoomId = source.getHotelRoom().getId();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getHotelRoomId() {
		return hotelRoomId;
	}
	public void setHotelRoomId(Long hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}
}
