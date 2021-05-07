package com.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.HotelRoomDTO;
import com.model.HotelRoom;
@Component
public class ToHotelRoomDTOConverter implements Converter<HotelRoom, HotelRoomDTO> {

	@Override
	public HotelRoomDTO convert(HotelRoom source) {
		
		return new HotelRoomDTO(source);
	}
public List<HotelRoomDTO> convert(List<HotelRoom> hotelRooms){
		
		List<HotelRoomDTO> retValue = new ArrayList<>();
		
		for (HotelRoom hotelRoom : hotelRooms) {
			retValue.add(this.convert(hotelRoom));
		}
		
		return retValue;
	}
}
