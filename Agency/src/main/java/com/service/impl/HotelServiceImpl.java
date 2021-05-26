package com.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Hotel;
import com.repository.HotelRepository;
import com.service.HotelService;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepo;
	@Override
	public Hotel save(Hotel convert) {

		
			return hotelRepo.save(convert) ;
	
	}
	@Override
	public List<Hotel> getAll() {
		
		return hotelRepo.findAll();
	}
	@Override
	public List<Hotel> getAllByCity(Long id) {
		
		return hotelRepo.findByCityId(id);
	}
	
	@Override
	public Hotel delete(Long id) {
		Optional<Hotel> optional= hotelRepo.findById(id);
		if(optional.isPresent()) {
			hotelRepo.deleteById(id);
			return optional.get();
		}
		return null;
	}
	@Override
	public Hotel findById(Long id) {
		Optional<Hotel> optional= hotelRepo.findById(id);
		if(optional.isPresent()) {
			
			return optional.get();
		}
		return null;
	}

}
