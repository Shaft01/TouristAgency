package com.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.model.Country;
import com.repository.CountryRepository;
import com.service.CountryService;

import reactor.core.publisher.Flux;

@Service
@Transactional
public class CountryServiceImpl implements CountryService{
	private WebClient webClient = WebClient.create("http://localhost:8081");
	@Autowired
	CountryRepository countryRepo;
	
	@Override
	public List<Country> getAll() {
		
		return countryRepo.findAll();
	}
	@Override
	public Country save(Country convert) {
		if(convert.getId()!=null) {
			
			return countryRepo.save(convert);
		}else {
			Country c= countryRepo.findByName(convert.getName());
			if(c!=null) {
				return null;
			}else {
				return countryRepo.save(convert);
			}
		}
	}
	@Override
	public Country delete(Long id) {
		
		Optional<Country> optional = countryRepo.findById(id);
		
		if(optional.isPresent()) {
			countryRepo.deleteById(id);
			
			return optional.get();
		}
		
		return null;
	}
	@Override
	public Country findById(Long id) {
		Optional<Country> optional = countryRepo.findById(id);
		
		if(optional.isPresent()) {
			
			return optional.get();
		}
		return null;
	}
	@Override
	public Flux<Country> getAllRemote() {
		return  webClient.get().uri("/api/countries").retrieve()
		        .bodyToFlux(Country.class);
	}
	@Override
	public List<Country> getRandom() {
		Long count=countryRepo.count()/3;
		int idx = (int)(Math.random() * count);
		
		Page<Country>page=countryRepo.findAll(PageRequest.of(idx, 3));
		List<Country> countries=new ArrayList<>();
		if(page.hasContent()) {
			countries=page.getContent();
		}
		return countries;
	}
	

}
