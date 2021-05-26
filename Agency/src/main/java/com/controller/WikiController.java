package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.WikiService;

@RestController
@RequestMapping(value = "/api/wiki")
public class WikiController {

	@Autowired
	private WikiService wikiService;
	
	@GetMapping()
	public ResponseEntity<Map<String,String>> getWikiInfo(@RequestParam("title") String title){
		String info= wikiService.getInfo(title);
		Map<String,String> content=new HashMap<>();
		content.put("content", info);
		return new ResponseEntity<>(content,HttpStatus.OK);
	}
}
