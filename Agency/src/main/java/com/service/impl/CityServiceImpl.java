package com.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.City;
import com.repository.CityRepository;
import com.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository cityRepo;
	
	@Override
	public City save(City convert) {
		
		return cityRepo.save(convert);
	}

	@Override
	public List<City> getByCountry(Long id) {
		
		return cityRepo.findByCountryId(id);
	}

	@Override
	public List<City> getAll() {
		
		return cityRepo.findAll();
	}

}
