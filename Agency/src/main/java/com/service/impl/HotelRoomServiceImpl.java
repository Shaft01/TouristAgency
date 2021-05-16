package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.HotelRoom;
import com.repository.HotelRoomRepository;
import com.service.HotelRoomService;

@Service
@Transactional
public class HotelRoomServiceImpl implements HotelRoomService {
	@Autowired
	private HotelRoomRepository hotelRoomRepo;
	@Override
	public HotelRoom save(HotelRoom convert) {
		
		return hotelRoomRepo.save(convert);
	}

	@Override
	public List<HotelRoom> getAll() {
		
		return hotelRoomRepo.findAll();
	}

}
