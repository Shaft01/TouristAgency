package com.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.CityDTO;
import com.model.City;


@Component
public class ToCityDTOConverter implements Converter<City, CityDTO> {

	@Override
	public CityDTO convert(City source) {
		
		return new CityDTO(source);
	}
	public List<CityDTO> convert(List<City> cities){
	List<CityDTO> retValue = new ArrayList<>();
		
		for (City city : cities) {
			retValue.add(this.convert(city));
		}
		
		return retValue;
	}

}
