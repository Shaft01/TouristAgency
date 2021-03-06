package com.service.impl;

import java.util.List;
import java.util.Optional;

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
		if(convert.getId() != null) {
			return cityRepo.save(convert);
		}else {
			City c= cityRepo.findByNameAndCountry(convert.getName(), convert.getCountry());
			if(c!=null) {
				return null;
			}else {
				return cityRepo.save(convert);
				
			}
		}
			

	}

	@Override
	public List<City> getByCountry(Long id) {
		
		return cityRepo.findByCountryId(id);
	}

	@Override
	public List<City> getAll() {
		
		return cityRepo.findAll();
	}

	@Override
	public City delete(Long id) {
		Optional<City> optional= cityRepo.findById(id);
		if(optional.isPresent()) {
			cityRepo.deleteById(id);
			
			return optional.get();
		}
		return null;
	}

	@Override
	public City findById(Long id) {
		Optional<City> optional= cityRepo.findById(id);
		if(optional.isPresent()) {
			
			return optional.get();
		}
		return null;
	}

}
