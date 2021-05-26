package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RequestUserDTO;
import com.model.Authority;
import com.model.User;
import com.security.auth.JwtAuthenticationRequest;
import com.security.auth.ResourceConflictException;
import com.security.auth.TemporarilyBlockedException;
import com.security.auth.TokenUtils;
import com.security.auth.UserTokenState;
import com.service.UserService;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;




@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		Authentication authentication = null;
		try {
			authentication = userService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch (TemporarilyBlockedException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		if(authentication == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = (User) authentication.getPrincipal();
		
		String roleName="";
		
		@SuppressWarnings("unchecked")
		Collection<Authority> collection = (Collection<Authority>) user.getAuthorities();
		if(collection!=null) {
			Iterator<Authority> iterator = collection.iterator();
			while(iterator.hasNext()) {
				roleName  = iterator.next().getName();
			}
		}
		
		
		String jwt = tokenUtils.generateToken(user.getUsername(), roleName);
		int expiresIn = tokenUtils.getExpiredIn();

		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody RequestUserDTO userRequest) {
		
		User existUser = this.userService.findByUsername(userRequest.getUsername());
		if (existUser != null) {
			throw new ResourceConflictException(userRequest.getId(), "Username already exists");
		}
//		if(!satisfiesPasswordPolicy(userRequest.getPassword())) {
//			Map<String, String> result = new HashMap<>();
//			result.put("result", "failure");
//			result.put("reason", "Password must contain an upper case letter, lower case letter and a number. It should be 8 characters long, without whitespaces.");
//			return ResponseEntity.badRequest().body(result);
//		}

		User user = this.userService.save(userRequest);
		HttpHeaders headers = new HttpHeaders();
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}


}
