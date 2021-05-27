package com.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Arrangement;
import com.repository.ArrangementRepository;
import com.service.ArrangementService;

@Service
@Transactional
public class ArrangementServiceImpl implements ArrangementService {
	@Autowired
	private ArrangementRepository arrangementRepo;
	@Override
	public Arrangement save(Arrangement arrangement) {
		
		return arrangementRepo.save(arrangement);
	}
	@Override
	public List<Arrangement> findByUser(String username) {
		
		return arrangementRepo.findByUser(username);
	}
	@Override
	public List<Arrangement> findByUserAndHotel(String username, Long id) {
		
		return arrangementRepo.findByUserAndHotel(username, id);
	}
	@Override
	public Arrangement delete(Long id) {
		Optional<Arrangement> optional=arrangementRepo.findById(id);
		if(optional.isPresent()) {
			arrangementRepo.deleteById(id);
			return optional.get();
		}
		return null;
	}

}
