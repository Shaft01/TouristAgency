package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.City;
import com.model.Country;

public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByCountryId(Long id);
	City findByName(String name);
	City findByNameAndCountry(String name, Country country);

}
