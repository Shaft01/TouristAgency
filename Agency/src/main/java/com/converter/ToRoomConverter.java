package com.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.RoomDTO;
import com.model.Room;
@Component
public class ToRoomConverter implements Converter<RoomDTO,Room>{

	@Override
	public Room convert(RoomDTO source) {
		Room room= new Room();
		room.setId(source.getId());
		room.setType(source.getType());
		return room;
	}

}
