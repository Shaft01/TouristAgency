package com.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.ArrangementDTO;
import com.model.Arrangement;
import com.model.HotelRoom;
import com.model.User;
import com.repository.HotelRoomRepository;
import com.repository.UserRepository;
@Component
public class ToArrangementConverter implements Converter<ArrangementDTO,Arrangement>{
	@Autowired
	private HotelRoomRepository hotelRoomRepo;
	@Autowired
	private UserRepository userRepo;
	@Override
	public Arrangement convert(ArrangementDTO source) {
		Arrangement arrangement= new Arrangement();
		arrangement.setStartDate(source.getStartDate());
		arrangement.setEndDate(source.getEndDate());
		arrangement.setDetails(source.getDetails());
		arrangement.setPrice(source.getPrice());
		if(source.getHotelRoomId()!=null) {
			Optional<HotelRoom> optional= hotelRoomRepo.findById(source.getHotelRoomId());
			if(optional.isPresent()) {
				arrangement.setRooms(optional.get());
			}
		}
		if(source.getUserId()!=null) {
			Optional<User> optional = userRepo.findById(source.getUserId());
			if(optional.isPresent()) {
				arrangement.setUser(optional.get());
			}
		}
		return arrangement;
	}

}
