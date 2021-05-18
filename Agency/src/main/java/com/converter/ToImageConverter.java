package com.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.ImageDTO;
import com.model.HotelRoom;
import com.model.Image;
import com.repository.HotelRoomRepository;
@Component
public class ToImageConverter implements Converter<ImageDTO,Image> {
	@Autowired
	HotelRoomRepository hotelRoomRepo;
	@Override
	public Image convert(ImageDTO source) {
		Image image=new Image();
		image.setId(source.getId());
		image.setPath(source.getPath());
		if(source.getHotelRoomId()!=null) {
			Optional<HotelRoom> optional=hotelRoomRepo.findById(source.getHotelRoomId());
			if(optional.isPresent())
				image.setHotelRoom(optional.get());
		}
		return image;
	}
	
	
}
