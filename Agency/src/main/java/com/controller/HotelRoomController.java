package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.converter.ToHotelRoomConverter;
import com.converter.ToHotelRoomDTOConverter;
import com.dto.HotelRoomDTO;
import com.model.Hotel;
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
	@GetMapping("get-by-hotel")
	public ResponseEntity<List<HotelRoomDTO>> getAllByHotel(@RequestParam("hotelId") Long id){
		List<HotelRoom> list = hotelRoomService.getAllByHotel(id);
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<HotelRoomDTO> getOne(@PathVariable Long id){
		HotelRoom hotelRoom= hotelRoomService.findById(id);
		
		if(hotelRoom==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(toDTO.convert(hotelRoom),HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<HotelRoomDTO> update(@PathVariable Long id,@RequestBody HotelRoomDTO room){
		if(!id.equals(room.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		HotelRoom persisted = hotelRoomService.save(
				toEntity.convert(room));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<HotelRoomDTO> delete(@PathVariable Long id){
		HotelRoom deleted= hotelRoomService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted),
				HttpStatus.OK);
	}
}
