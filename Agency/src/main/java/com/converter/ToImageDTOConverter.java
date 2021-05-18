package com.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.ImageDTO;
import com.model.Image;
@Component
public class ToImageDTOConverter implements Converter<Image,ImageDTO> {

	@Override
	public ImageDTO convert(Image source) {
		
		return new ImageDTO(source);
	}
	public List<ImageDTO> convert(List<Image> source){
		List<ImageDTO> images=new ArrayList<>();
		for(Image image: source) {
			images.add(this.convert(image));
		}
		return images;
	}

}
