package com.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Country;
import com.repository.CountryRepository;
import com.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryRepository countryRepo;
	@Override
	public List<Country> getAll() {
		
		return countryRepo.findAll();
	}
	@Override
	public Country save(Country convert) {
		Country c = countryRepo.findByName(convert.getName());
		if(c == null) {
			return countryRepo.save(convert);
		}
		return null;
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

}
