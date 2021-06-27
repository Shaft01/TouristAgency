package com.service;

import java.util.List;


import com.model.Country;

import reactor.core.publisher.Flux;

public interface CountryService {

	List<Country> getAll();

	Country save(Country convert);

	Country delete(Long id);

	Country findById(Long id);

	Flux<Country> getAllRemote();

	List<Country> getRandom();

	

}
