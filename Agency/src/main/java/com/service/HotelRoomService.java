package com.service;

import java.util.List;

import com.model.HotelRoom;

public interface HotelRoomService {

	HotelRoom save(HotelRoom convert);

	List<HotelRoom> getAll();

}
