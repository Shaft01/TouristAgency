package com.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.CityDTO;
import com.model.City;
import com.model.Country;
import com.repository.CountryRepository;
@Component
public class ToCityConverter implements Converter<CityDTO, City> {
	
	@Autowired
	private CountryRepository countryRepo;
	@Override
	public City convert(CityDTO source) {
		City city = new City();
		city.setId(source.getId());
		city.setName(source.getName());
		city.setImagePath(source.getImagePath());
		if(source.getCountryId() != null) {
			Optional<Country> country = countryRepo.findById(source.getCountryId());
			if(country.isPresent()) {
				city.setCountry(country.get());
			}
		}
		return city;
	}

}
