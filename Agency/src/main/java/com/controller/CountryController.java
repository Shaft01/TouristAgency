package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.converter.ToCountryConverter;
import com.converter.ToCountryDTOConverter;
import com.dto.CountryDTO;
import com.model.City;
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
	
	@GetMapping("get-all-remote")
	public ResponseEntity<List<CountryDTO>> getAllRemote(){
		List<Country> list =countryService.getAllRemote().collectList().block();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
		
	}
	@GetMapping("get-random")
	public ResponseEntity<List<CountryDTO>> getRandom(){
		List<Country> list=countryService.getRandom();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
	@GetMapping()
	public ResponseEntity<List<CountryDTO>> getAll(){
		List<Country> list = countryService.getAll();
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<CountryDTO> insert(@RequestBody CountryDTO country){
		
		Country newCountry = countryService.save(toEntity.convert(country));
		if(newCountry==null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(newCountry),HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CountryDTO> findById(@PathVariable Long id){
		
		Country country= countryService.findById(id);
		
		return new ResponseEntity<>(toDTO.convert(country),HttpStatus.OK);
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<CountryDTO> delete(@PathVariable Long id){
		Country deleted = countryService.delete(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted),
				HttpStatus.OK);
	}
	@PutMapping("{id}")
	public ResponseEntity<CountryDTO> update(@PathVariable Long id,@RequestBody CountryDTO country){
		if(!id.equals(country.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Country persisted = countryService.save(
				toEntity.convert(country));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.OK);
	}
}
