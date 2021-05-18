package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.City;
import com.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

	List<Hotel> findByCityId(Long id);

	Hotel findByNameAndCity(String name, City city);

}
