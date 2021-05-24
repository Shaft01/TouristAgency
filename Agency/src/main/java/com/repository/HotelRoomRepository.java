package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.HotelRoom;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

	List<HotelRoom> findByHotelId(Long id);

}
