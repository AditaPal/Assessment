package com.java.assessment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.assessment.entity.Nace;

@Repository
public interface NaceRepository extends JpaRepository<Nace, Long> {
	
	Optional<Nace> findByOrder(int order);
}
