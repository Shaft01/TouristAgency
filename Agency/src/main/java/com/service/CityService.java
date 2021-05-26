package com.service;

import java.util.List;

import com.model.City;

public interface CityService {

	City save(City convert);

	List<City> getByCountry(Long id);

	List<City> getAll();

	City delete(Long id);

	City findById(Long id);

}
