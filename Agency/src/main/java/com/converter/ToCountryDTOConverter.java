package com.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.CountryDTO;
import com.model.Country;

@Component
public class ToCountryDTOConverter implements Converter<Country, CountryDTO> {

	@Override
	public CountryDTO convert(Country source) {
		
		return new CountryDTO(source);
	}
public List<CountryDTO> convert(List<Country> countries){
		
		List<CountryDTO> retValue = new ArrayList<>();
		
		for (Country country : countries) {
			retValue.add(this.convert(country));
		}
		
		return retValue;
	}

}
