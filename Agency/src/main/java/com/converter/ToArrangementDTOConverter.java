package com.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.ArrangementDTO;
import com.model.Arrangement;
@Component
public class ToArrangementDTOConverter implements Converter<Arrangement,ArrangementDTO> {

	@Override
	public ArrangementDTO convert(Arrangement source) {
		
		return new ArrangementDTO(source);
	}
	
	public List<ArrangementDTO> convert(List<Arrangement> source){
		List<ArrangementDTO> retValue = new ArrayList<>();
			
			for (Arrangement arrangement : source) {
				retValue.add(this.convert(arrangement));
			}
			
			return retValue;
		}

}
