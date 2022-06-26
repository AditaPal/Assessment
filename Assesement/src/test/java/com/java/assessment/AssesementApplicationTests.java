package com.java.assessment;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.java.assessment.entity.Nace;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql("classpath:data.sql")
class AssesementApplicationTests {

	private String baseUrl = "http://localhost:";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testSaveDetails() {

		Nace nace = new Nace();
		nace.setCode("A");
		nace.setDescription("AGRICULTURE, FORESTRY AND FISHING");
		nace.setLevel(1);
		nace.setOrder(398431);
		nace.setParent(null);
		nace.setReference("A");
		nace.setRulings(null);
		nace.setThisItemAlsoIncludes(null);
		nace.setThisItemExcludes(null);
		nace.setThisItemIncludes(null);
		nace.setThisItemIncludes(
				"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.");

		ResponseEntity<Long> entity = restTemplate.postForEntity(createURLWithPort("/nace/saveNaceDetails"), nace,
				Long.class);
		assertNotNull(entity.getBody());
		assertEquals(201, entity.getStatusCodeValue());

	}

	@Test
	public void testFindByOrderId() {
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<Nace> response = restTemplate.exchange(createURLWithPort("/nace/naceDetails/398431"),
				HttpMethod.GET, entity, Nace.class);

		assertNotNull(response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void testFindByOrderIdNotFound() {
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<Nace> response = restTemplate.exchange(createURLWithPort("/nace/naceDetails/398481"),
				HttpMethod.GET, entity, Nace.class);

		assertNull(response.getBody());
		assertEquals(404, response.getStatusCodeValue());
	}

	private String createURLWithPort(String uri) {
		return baseUrl + port + uri;
	}

}