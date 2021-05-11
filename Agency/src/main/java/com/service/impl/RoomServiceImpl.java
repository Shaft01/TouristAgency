package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Room;
import com.repository.RoomRepository;
import com.service.RoomService;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
	@Autowired
	RoomRepository roomRepo;
	@Override
	public List<Room> getAll() {
		
		return roomRepo.findAll();
	}

	@Override
	public Room save(Room convert) {
		
		return roomRepo.save(convert);
	}

}
