package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.converter.ToCountryConverter;
import com.converter.ToCountryDTOConverter;
import com.dto.CountryDTO;
import com.model.Country;
import com.service.CountryService;

@RestController
@RequestMapping(value = "/api/country")
public class CountryController {
	@Autowired
	ToCountryDTOConverter toDTO;
	@Autowired
	CountryService countryService;
	@Autowired
	ToCountryConverter toEntity;
	
	@GetMapping()
	public ResponseEntity<List<CountryDTO>> getAll(){
		List<Country> list = countryService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<CountryDTO> insert(@RequestBody CountryDTO country){
		
		Country newCountry = countryService.save(toEntity.convert(country));
		
		return new ResponseEntity<>(toDTO.convert(newCountry),HttpStatus.CREATED);
	}
	@GetMapping("{id}")
	public ResponseEntity<CountryDTO> findById(@PathVariable Long id){
		
		Country country= countryService.findById(id);
		
		return new ResponseEntity<>(toDTO.convert(country),HttpStatus.OK);
		
	}
	@DeleteMapping("id")
	public ResponseEntity<CountryDTO> delete(@PathVariable Long id){
		Country deleted = countryService.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted),
				HttpStatus.OK);
	}
}
