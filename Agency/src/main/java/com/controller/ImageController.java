package com.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.converter.ToImageDTOConverter;
import com.dto.ImageDTO;
import com.model.Image;
import com.service.ImageService;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {
	@Autowired
	private ImageService imageService;
	@Autowired
	private ToImageDTOConverter toDTO;
	@PostMapping("{id}")
	public void uploadImage (@RequestParam("image") MultipartFile image,@RequestParam("type")String type,@PathVariable() Long id) {
		try {
			imageService.save(image,type,id);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@GetMapping("get-images-by-room/{id}")
	public ResponseEntity<List<ImageDTO>> getImagesByRoom(@PathVariable Long id){
		List<Image> images= imageService.getImagesByRoom(id);
		return new ResponseEntity<>(toDTO.convert(images),HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<InputStreamResource> getImage(@PathVariable Long id){
		InputStreamResource resources = imageService.openImage(id);
		return  ResponseEntity.ok().contentType(MediaType.parseMediaType("image/png")).body(resources);

	}
	@GetMapping("open-image")
	public ResponseEntity<InputStreamResource> getImage(@RequestParam String path){
		
			InputStreamResource resources = imageService.openImagePath(path);
			if(resources!=null) {
				return  ResponseEntity.ok().contentType(MediaType.parseMediaType("image/png")).body(resources);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
