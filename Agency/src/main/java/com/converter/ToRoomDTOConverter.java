package com.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.RoomDTO;
import com.model.Room;
@Component
public class ToRoomDTOConverter implements Converter<Room, RoomDTO> {

	@Override
	public RoomDTO convert(Room source) {
		
		return new RoomDTO(source);
	}
	public List<RoomDTO> convert (List<Room> source){
		List<RoomDTO> retValue = new ArrayList<>();
		
		for (Room room : source) {
			retValue.add(this.convert(room));
		}
		
		return retValue;
	}

}
