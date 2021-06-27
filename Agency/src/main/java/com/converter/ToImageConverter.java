package com.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.ImageDTO;
import com.model.Hotel;
import com.model.Image;
import com.repository.HotelRepository;
@Component
public class ToImageConverter implements Converter<ImageDTO,Image> {
	@Autowired
	HotelRepository hotelRepo;
	@Override
	public Image convert(ImageDTO source) {
		Image image=new Image();
		image.setId(source.getId());
		image.setPath(source.getPath());
		if(source.getHotelId()!=null) {
			Optional<Hotel> optional=hotelRepo.findById(source.getHotelId());
			if(optional.isPresent())
				image.setHotel(optional.get());
		}
		return image;
	}
	
	
}
