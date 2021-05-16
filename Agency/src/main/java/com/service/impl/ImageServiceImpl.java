package com.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.io.File;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.model.HotelRoom;
import com.model.Image;
import com.repository.HotelRoomRepository;
import com.repository.ImageRepository;
import com.service.ImageService;
@Service
@Transactional
public class ImageServiceImpl implements ImageService {
	@Value("${filesDir.path}")
	String filesPath;
	@Autowired
	ImageRepository imageRepo;
	@Autowired
	HotelRoomRepository hotelRoomRepo;
	
	@Override
	public Image save(MultipartFile image, Long hotelRoomId) {
		String name="img_"+System.currentTimeMillis()+".png";
		return save(image,name,hotelRoomId);
		
	}

	@Override
	public List<Image> getImagesByRoom(Long id) {
		
		return imageRepo.findByHotelRoomId(id);
	}

	@Override
	public InputStreamResource openImage(Long id) {
		try {
			Image image=imageRepo.getOne(id);
			Path path=Paths.get(filesPath,image.getPath());
			return new InputStreamResource(new FileInputStream(new File(path.toString())));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Image save(MultipartFile image, String name, Long hotelRoomId) {
		
		Path path=Paths.get(filesPath).resolve(name);
		try(OutputStream outputStream =new FileOutputStream(path.toString())){
			InputStream inputStream=image.getInputStream();
			Image newImage=new Image();
			
			newImage.setPath(name);
			Optional<HotelRoom> optional=hotelRoomRepo.findById(hotelRoomId);
			newImage.setHotelRoom(optional.isPresent()?  optional.get(): null);
			System.out.println(newImage.getHotelRoom().getId());
			
			int read = 0;
			byte[] buffer = new byte[1024];
			while ((read = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
			}
			outputStream.flush();
			inputStream.close();
			newImage=imageRepo.save(newImage);
			return newImage;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}


}
