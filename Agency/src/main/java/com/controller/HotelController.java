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

import com.converter.ToHotelConverter;
import com.converter.ToHotelDTOConverter;
import com.dto.CountryDTO;
import com.dto.HotelDTO;
import com.model.Country;
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
		if(newHotel==null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(newHotel),HttpStatus.CREATED);
	}
	@GetMapping()
	private ResponseEntity<List<HotelDTO>>getAll(){
		List<Hotel> list = hotelService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<HotelDTO> getOne(@PathVariable Long id){
		Hotel hotel = hotelService.findById(id);
		if(hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(hotel),
				HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HotelDTO> delete(@PathVariable Long id){
		Hotel deleted = hotelService.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted),
				HttpStatus.OK);
	}
	@GetMapping("get-by-city")
	 public ResponseEntity<List<HotelDTO>>getAllByCity(@RequestParam("cityId") Long id){
		List<Hotel> list = hotelService.getAllByCity(id);
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<HotelDTO> update(@PathVariable Long id,@RequestBody HotelDTO hotel){
		if(!id.equals(hotel.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Hotel persisted = hotelService.save(
				toEntity.convert(hotel));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.OK);
	}

}
