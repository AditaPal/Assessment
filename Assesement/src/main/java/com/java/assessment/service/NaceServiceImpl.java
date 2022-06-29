package com.java.assessment.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.assessment.csv.CSVUtil;
import com.java.assessment.entity.Nace;
import com.java.assessment.repository.NaceRepository;

@Service
public class NaceServiceImpl implements NaceService {

	@Autowired
	NaceRepository naceRepository;

	@Override
	public Optional<Nace> findNaceByOrderId(int order) {
		return naceRepository.findByOrder(order);
	}

	@Override
	public long saveNaceDetails(Nace nace) {
		naceRepository.save(nace);
		return nace.getId();
	}

	@Override
	public void saveNaceList(MultipartFile file) {
		try {
			List<Nace> naceList = CSVUtil.csvToNaceList(file.getInputStream());
			naceRepository.saveAll(naceList);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}
}
