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

import com.converter.ToCityConverter;
import com.converter.ToCityDTOConverter;
import com.dto.CityDTO;
import com.dto.CountryDTO;
import com.model.City;
import com.model.Country;
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
		if(newCity==null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(newCity),HttpStatus.CREATED);
	}
	@GetMapping("get-by-country")
	public ResponseEntity<List<CityDTO>>getByCountry(@RequestParam(value="countryId") Long id){
		
		List<City> list= cityService.getByCountry(id);
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.ACCEPTED);
	}
	@GetMapping()
	public ResponseEntity<List<CityDTO>> getAll(){
		List<City> list= cityService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<CityDTO> delete(@PathVariable Long id){
		City deleted = cityService.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted),
				HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<CityDTO> update(@PathVariable Long id,@RequestBody CityDTO city){
		if(!id.equals(city.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		City persisted = cityService.save(
				toEntity.convert(city));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.OK);
	}
}
