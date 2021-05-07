package com.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.dto.HotelDTO;

import com.model.Hotel;

@Component
public class ToHotelDTOConverter implements Converter<Hotel, HotelDTO>{

	@Override
	public HotelDTO convert(Hotel source) {
		
		return new HotelDTO(source);
	}
	
public List<HotelDTO> convert(List<Hotel> hotels){
		
		List<HotelDTO> retValue = new ArrayList<>();
		
		for (Hotel hotel : hotels) {
			retValue.add(this.convert(hotel));
		}
		
		return retValue;
	}
	
}
