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

import com.converter.ToCityConverter;
import com.converter.ToCityDTOConverter;
import com.dto.CityDTO;
import com.model.City;
import com.service.CityService;

@RestController
@RequestMapping(value = "/api/city")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@Autowired
	private ToCityDTOConverter toDTO;
	
	@Autowired
	private ToCityConverter toEntity;
	
	@PostMapping()
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO city){
		
		City newCity = cityService.save(toEntity.convert(city));
	
		return new ResponseEntity<>(toDTO.convert(newCity),HttpStatus.CREATED);
	}
	@GetMapping("get-by-country")
	public ResponseEntity<List<CityDTO>>getByCountry(@RequestParam(value="countryId") Long id){
		
		List<City> list= cityService.getByCountry(id);
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.ACCEPTED);
	}@GetMapping()
	public ResponseEntity<List<CityDTO>> getAll(){
		List<City> list= cityService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
}
