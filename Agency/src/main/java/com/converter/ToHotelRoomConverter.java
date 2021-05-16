package com.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.HotelRoomDTO;
import com.model.Hotel;
import com.model.HotelRoom;
import com.model.Room;
import com.repository.HotelRepository;
import com.repository.RoomRepository;
@Component
public class ToHotelRoomConverter implements Converter<HotelRoomDTO, HotelRoom> {

	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	@Override
	public HotelRoom convert(HotelRoomDTO source) {
		HotelRoom hotelRoom = new HotelRoom();
		hotelRoom.setId(source.getId());
		hotelRoom.setPricePerDay(source.getPricePerDay());
		if(source.getHotelId()!=null) {
			Optional<Hotel> optional = hotelRepo.findById(source.getHotelId());
			if(optional.isPresent()) {
				hotelRoom.setHotel(optional.get());
			}
		}
		if(source.getRoomId()!=null) {
			Optional<Room> optional = roomRepo.findById(source.getRoomId());
			if(optional.isPresent()) {
				hotelRoom.setRoom(optional.get());
			}
		}
		return hotelRoom;
	}
	public List<HotelRoom> convert(List<HotelRoomDTO> hotelRooms){
		
		List<HotelRoom> retValue = new ArrayList<>();
		
		for ( HotelRoomDTO hotelRoom : hotelRooms) {
			retValue.add(this.convert(hotelRoom));
		}
		
		return retValue;
	}
}
