package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.converter.ToUserLoginDTO;
import com.dto.UserDTO;
import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ToUserLoginDTO toDTO;
	@GetMapping()
	public ResponseEntity<UserDTO>findByUsername(@RequestParam("username")String username){
		
		User user= userService.findByUsername(username);
		
		return new ResponseEntity<>(toDTO.convert(user),HttpStatus.OK);
	}

}
