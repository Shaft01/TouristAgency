package com.dto;

import com.model.Room;

public class RoomDTO {
	private Long id;
	private String type;
	
	public RoomDTO() {
		
	}
	public RoomDTO(Room source) {
		this.id = source.getId();
		this.type = source.getType();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
