package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.HotelRoom;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

}
