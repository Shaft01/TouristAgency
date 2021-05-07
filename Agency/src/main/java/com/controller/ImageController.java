package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dto.ImageDTO;
import com.model.Image;
import com.service.ImageService;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {
	@Autowired
	private ImageService imageService;
	@PostMapping("{hotelRoomId}")
	public void uploadImage (@RequestParam("image")MultipartFile image,@PathVariable() Long hotelRoomId) {
		imageService.save(image,hotelRoomId);
	}
	@GetMapping("get-images-by-room/{id}")
	public ResponseEntity<List<ImageDTO>> getImagesByRoom(@PathVariable Long id){
		List<Image> images= imageService.getImagesByRoom(id);
		return null;
	}
	@GetMapping("{id}")
	public ResponseEntity<InputStreamResource> getImage(@PathVariable Long id){
		InputStreamResource resources = imageService.openImage(id);
		return  ResponseEntity.ok().contentType(MediaType.parseMediaType("image/png")).body(resources);

	}
}
