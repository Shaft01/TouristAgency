package com.service;


import java.util.List;

import com.model.Arrangement;

public interface ArrangementService {

	Arrangement save(Arrangement arrangement);

	List<Arrangement> findByUser(String username);

	List<Arrangement> findByUserAndHotel(String username, Long id);

	Arrangement delete(Long id);
	

}
