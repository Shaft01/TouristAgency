package com.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
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

import com.model.City;
import com.model.Country;
import com.model.Hotel;
import com.model.HotelRoom;
import com.model.Image;
import com.repository.CityRepository;
import com.repository.CountryRepository;
import com.repository.HotelRepository;
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
	@Autowired
	CountryRepository countryRepo;
	@Autowired
	CityRepository cityRepo;
	@Autowired
	HotelRepository hotelRepo;
	@Override
	public Boolean save(MultipartFile image,String type, Long id) throws IOException {
		String name="img_"+System.currentTimeMillis()+".png";
		if(type.equals("Country")) {
			saveCountryImage(name,id);
		}
		else if(type.equals("City")) {
			saveCityImage(name,id);
		}else if(type.equals("Image")) {
			saveHotelImage(name,id);
		}else if(type.equals("Hotel")) {
			saveHotelPathImage(name,id);
		}
		
		return saveImage(image,name);
		
	}
	private void saveHotelPathImage(String name,Long id) {
		Optional<Hotel> hotel= hotelRepo.findById(id);
		if(hotel.isPresent()) {
			Hotel h=hotel.get();
			h.setImagePath(name);
			hotelRepo.save(h);
		}
	}
	private void saveHotelImage(String name,Long id) {
		Image newImage=new Image();
		
		newImage.setPath(name);
		Optional<Hotel> optional=hotelRepo.findById(id);
		newImage.setHotel(optional.isPresent()?  optional.get(): null);
		imageRepo.save(newImage);
	}
	private void saveCountryImage(String name,Long id) {
		Optional<Country> country=countryRepo.findById(id);
		if(country.isPresent()) {
			Country c=country.get();
			c.setImagePath(name);
			countryRepo.save(c);
		}
	}
	
	private void saveCityImage(String name,Long id) {
		Optional<City> city= cityRepo.findById(id);
		if(city.isPresent()) {
			City c= city.get();
			c.setImagePath(name);
			cityRepo.save(c);
		}
	}
	@Override
	public List<Image> getImagesByRoom(Long id) {
		
		return imageRepo.findByHotelId(id);
	}
	@Override
	public InputStreamResource openImagePath(String path) {
		try {
			
			Path paths=Paths.get(filesPath,path);
			return new InputStreamResource(new FileInputStream(new File(paths.toString())));
		}catch(FileNotFoundException e) {
			
			return null;
		}
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
	
	public Boolean saveImage(MultipartFile image, String name) throws IOException {
		Path path=Files.createDirectories(Paths.get(filesPath));
		Path paths=path.resolve(name);
		
		try(OutputStream outputStream =new FileOutputStream(paths.toString())){
			InputStream inputStream=image.getInputStream();

			int read = 0;
			byte[] buffer = new byte[1024];
			while ((read = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
			}
			outputStream.flush();
			inputStream.close();
			
			return true;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}


}
