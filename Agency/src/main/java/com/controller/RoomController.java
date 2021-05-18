package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.converter.ToRoomConverter;
import com.converter.ToRoomDTOConverter;
import com.dto.RoomDTO;
import com.model.Room;
import com.service.RoomService;

@RestController
@RequestMapping(value = "/api/room")
public class RoomController {
	
	@Autowired
	RoomService roomService;
	@Autowired
	ToRoomConverter toEntity;
	@Autowired
	ToRoomDTOConverter toDTO;
	@GetMapping()
	public ResponseEntity<List<RoomDTO>>getAll(){
		
		List<Room> rooms = roomService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(rooms),HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<RoomDTO> insert(@RequestBody RoomDTO room){
		Room newRoom = roomService.save(toEntity.convert(room));
		if(newRoom==null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(newRoom),HttpStatus.OK);
	}
}
