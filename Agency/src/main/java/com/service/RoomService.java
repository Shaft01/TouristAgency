package com.service;

import java.util.List;

import com.model.Room;

public interface RoomService {

	List<Room> getAll();

	Room save(Room convert);

}
