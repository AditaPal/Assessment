package com.java.assessment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.assessment.entity.Nace;
import com.java.assessment.repository.NaceRepository;

@Service
public class NaceServiceImpl implements NaceService{

	@Autowired
	NaceRepository naceRepository;

	@Override
	public Optional<Nace> findNaceByOrderId(int order) {
		return naceRepository.findByOrder(order);
	}
	
	@Override
	public long saveNaceDetails(Nace nace) {
		 naceRepository.save(nace) ;
		return nace.getId();
	}
}
