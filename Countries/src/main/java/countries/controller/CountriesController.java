package countries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import countries.dto.Country;
import countries.service.CountriesService;


@RestController
@RequestMapping(value = "/api/countries")
public class CountriesController {
	@Autowired
	CountriesService countriesService;
	@GetMapping()
	public ResponseEntity<List<Country>> getAll(){
		List<Country> countries=countriesService.getAll().collectList().block();
		
		return new ResponseEntity<>(countries,HttpStatus.OK);
		
	}

}
