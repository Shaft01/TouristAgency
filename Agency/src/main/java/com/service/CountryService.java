package com.service;

import java.util.List;

import com.model.Country;

public interface CountryService {

	List<Country> getAll();

	Country save(Country convert);

	Country delete(Long id);

	Country findById(Long id);

}
