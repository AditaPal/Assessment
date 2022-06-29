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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.assessment.csv.CSVUtil;
import com.java.assessment.entity.Nace;
import com.java.assessment.exceptionHandling.DetailsNotFound;
import com.java.assessment.service.NaceService;
import com.java.assessment.service.NaceServiceException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/nace")
public class NaceController {

	@Autowired
	NaceService naceService;

	@GetMapping("/naceDetails/{orderId}")
	@ApiOperation(value = "Fecthed Nace Item details by Order Id", response = Nace.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Nace details Fetched Successfully"),
			@ApiResponse(code = 404, message = "Nace details Not Found , order id is not present") })

	public ResponseEntity<Nace> getNaceByOrderId(@PathVariable("orderId") int orderId) throws DetailsNotFound {
		Optional<Nace> naceDetails = naceService.findNaceByOrderId(orderId);
		if (naceDetails.isPresent()) {
			return new ResponseEntity<>(naceDetails.get(), HttpStatus.OK);
		} else {
			// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new DetailsNotFound("Details not found");
		}
	}

	@PostMapping("/saveNaceDetails")
	@ApiOperation(value = "Persist Nace Item details in database", response = Long.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Nace details Saved Successfully"),
			@ApiResponse(code = 500, message = "System Error , not able to save data.") })
	public ResponseEntity<Long> saveNaceDetails(@RequestBody Nace nace) throws NaceServiceException {
		try {
			long id = naceService.saveNaceDetails(nace);
			return new ResponseEntity<>(id, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new NaceServiceException("Internal Server Exception while getting exception");
		}
	}

	@PostMapping("/uploadNaceDetails")
	@ApiOperation(value = "Persist Nace Item details in database from CSV File", response = Long.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Nace details Uploaded Successfully"),
			@ApiResponse(code = 500, message = "System Error , not able to upload data.") })
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws NaceServiceException {
		String message = "";
		if (CSVUtil.hasCSVFormat(file)) {
			try {
				naceService.saveNaceList(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				throw new NaceServiceException(message);
			}
		}
		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
}
