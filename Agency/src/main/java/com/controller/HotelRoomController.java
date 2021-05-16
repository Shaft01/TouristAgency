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

import com.converter.ToHotelRoomConverter;
import com.converter.ToHotelRoomDTOConverter;
import com.dto.HotelRoomDTO;
import com.model.HotelRoom;
import com.service.HotelRoomService;

@RestController
@RequestMapping(value = "/api/hotelRoom")
public class HotelRoomController {
	@Autowired
	HotelRoomService hotelRoomService;
	@Autowired
	ToHotelRoomDTOConverter toDTO;
	@Autowired
	ToHotelRoomConverter toEntity;
	@PostMapping()
	public ResponseEntity<HotelRoomDTO> insert(@RequestBody HotelRoomDTO hotelRoomDTO){
		
		HotelRoom newHotelRoom = hotelRoomService.save(toEntity.convert(hotelRoomDTO));
		
		return new ResponseEntity<>(toDTO.convert(newHotelRoom),HttpStatus.CREATED);
	}
	@GetMapping()
	public ResponseEntity<List<HotelRoomDTO>> getAll(){
		List<HotelRoom> list = hotelRoomService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
}
