package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

	List<Image> findByHotelId(Long id);

}
