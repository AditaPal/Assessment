package com.java.assessment.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.java.assessment.entity.Nace;

public interface NaceService {

	Optional<Nace> findNaceByOrderId(int order);

	long saveNaceDetails(Nace nace);

	void saveNaceList(MultipartFile file);
}
