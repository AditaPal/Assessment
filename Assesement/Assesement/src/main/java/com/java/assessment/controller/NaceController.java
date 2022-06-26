package com.java.assessment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.assessment.entity.Nace;
import com.java.assessment.service.NaceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/nace")
public class NaceController {

	@Autowired
	NaceService naceService;

	@GetMapping("/naceDetails/{orderId}")
	@ApiOperation(value="Fecthed Nace Item details by Order Id",response=Nace.class)
	@ApiResponses(value={@ApiResponse(code=200,message="Nace details Fetched Successfully"),
			@ApiResponse(code=404,message="Nace details Not Found , order id is not present")})

	public ResponseEntity<Nace> getNaceByOrderId(@PathVariable("orderId") int orderId) {
		Optional<Nace> naceDetails = naceService.findNaceByOrderId(orderId);
		if (naceDetails.isPresent()) {
			return new ResponseEntity<>(naceDetails.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/saveNaceDetails")
	@ApiOperation(value = "Persist Nace Item details in database", response = Long.class)
	@ApiResponses(value={@ApiResponse(code = 201, message = "Nace details Saved Successfully") 
	,@ApiResponse(code=500,message="System Error , not able to save data.")})
	public ResponseEntity<Long> saveNaceDetails(@RequestBody Nace nace) {
		try {
			long id = naceService.saveNaceDetails(nace);
			return new ResponseEntity<>(id, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
