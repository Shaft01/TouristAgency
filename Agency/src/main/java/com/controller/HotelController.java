package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.converter.ToHotelConverter;
import com.converter.ToHotelDTOConverter;
import com.dto.HotelDTO;
import com.model.Hotel;
import com.service.HotelService;

@RestController
@RequestMapping(value = "/api/hotel")
public class HotelController {

	@Autowired
	private ToHotelConverter toEntity;
	
	@Autowired
	private ToHotelDTOConverter toDTO;
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping()
	private ResponseEntity<HotelDTO> insert(@RequestBody HotelDTO hotel){
		
		Hotel newHotel = hotelService.save(toEntity.convert(hotel));
		
		return new ResponseEntity<>(toDTO.convert(newHotel),HttpStatus.CREATED);
	}
	@GetMapping()
	private ResponseEntity<List<HotelDTO>>getAll(){
		List<Hotel> list = hotelService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
	@GetMapping("get-by-cities")
	 private ResponseEntity<List<HotelDTO>>getAllByCity(@RequestParam Long id){
		List<Hotel> list = hotelService.getAllByCity(id);
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}

}
