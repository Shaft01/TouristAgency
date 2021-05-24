package com.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.HotelDTO;
import com.model.City;
import com.model.Hotel;
import com.repository.CityRepository;

@Component
public class ToHotelConverter implements Converter<HotelDTO, Hotel>{
	@Autowired
	CityRepository cityRepo;
	@Override
	public Hotel convert(HotelDTO source) {
		Hotel hotel=new Hotel();
		hotel.setId(source.getId());
		hotel.setName(source.getName());
		hotel.setDescription(source.getDescription());
		hotel.setImagePath(source.getImagePath());
		if(source.getCityId()!=null) {
			Optional<City> optional=cityRepo.findById(source.getCityId());
			if(optional.isPresent()) {
				hotel.setCity(optional.get());
			}
		}
		return hotel;
	}

}
