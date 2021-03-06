package com.service;

import java.util.List;

import com.model.Hotel;

public interface HotelService {

	Hotel save(Hotel convert);

	List<Hotel> getAll();

	List<Hotel> getAllByCity(Long id);

	Hotel delete(Long id);

	Hotel findById(Long id);

	
}
