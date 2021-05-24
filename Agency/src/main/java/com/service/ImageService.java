package com.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import com.model.Image;

public interface ImageService {

	Boolean save(MultipartFile image,String type, Long id) throws IOException;

	List<Image> getImagesByRoom(Long id);

	InputStreamResource openImage(Long id);

	InputStreamResource openImagePath(String path);

}
