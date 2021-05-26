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

import com.converter.ToArrangementConverter;
import com.converter.ToArrangementDTOConverter;
import com.dto.ArrangementDTO;
import com.model.Arrangement;
import com.service.ArrangementService;

@RestController
@RequestMapping(value = "/api/arrangement")
public class ArrangementController {

	@Autowired
	private ArrangementService arrangementService;
	@Autowired
	private ToArrangementDTOConverter toDTO;
	@Autowired
	private ToArrangementConverter toEntity;
	
	@PostMapping()
	public ResponseEntity<ArrangementDTO> save(@RequestBody ArrangementDTO arrangement){
		
		Arrangement newArrangement= arrangementService.save(toEntity.convert(arrangement));
		
		return new ResponseEntity<>(toDTO.convert(newArrangement),HttpStatus.OK);
	}
	
	@GetMapping("get-by-user")
	public ResponseEntity<List<ArrangementDTO>> getByUser(@RequestParam("username")String username){
		
		List<Arrangement> list= arrangementService.findByUser(username);
		
		return new ResponseEntity<>(toDTO.convert(list),HttpStatus.OK);
		
	}
}
