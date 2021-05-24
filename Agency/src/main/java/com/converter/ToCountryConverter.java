package com.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.CountryDTO;
import com.model.Country;

@Component
public class ToCountryConverter implements Converter<CountryDTO, Country> {

	@Override
	public Country convert(CountryDTO source) {
		Country country = new Country();
		country.setId(source.getId());
		country.setName(source.getName());
		country.setImagePath(source.getImagePath());
		country.setDescription(source.getDescription());
		return country;
	}
	

}
