package com.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import com.model.Image;

public interface ImageService {

	Image save(MultipartFile image, Long hotelRoomId);

	List<Image> getImagesByRoom(Long id);

	InputStreamResource openImage(Long id);

}
