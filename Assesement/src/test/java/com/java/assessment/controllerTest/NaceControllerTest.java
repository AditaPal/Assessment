package com.java.assessment.controllerTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.assessment.controller.NaceController;
import com.java.assessment.entity.Nace;
import com.java.assessment.exceptionHandling.DetailsNotFound;
import com.java.assessment.service.NaceService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NaceController.class)
public class NaceControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	NaceService naceService;

	@Test
	public void findNaceDetailsTest() throws Exception {
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
		nace.setThisItemIncludes(
				"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.");

		when(naceService.findNaceByOrderId(398431)).thenReturn(Optional.of(nace));
		this.mvc.perform(MockMvcRequestBuilders.get("/nace/naceDetails/398431"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void findNaceDetailsNotFoundTest() throws Exception {
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
		nace.setThisItemIncludes(
				"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.");

		when(naceService.findNaceByOrderId(398431)).thenReturn(Optional.of(nace));
		this.mvc.perform(MockMvcRequestBuilders.get("/nace/naceDetails/398432"))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof DetailsNotFound))
				.andExpect(result -> assertEquals("Details not found", result.getResolvedException().getMessage()));

	}

	@SuppressWarnings("deprecation")
	@Test
	public void saveNaceDetailsTest() throws Exception {
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
		nace.setThisItemIncludes(
				"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.");

		when(naceService.saveNaceDetails(nace)).thenReturn(new Long(298689));

		MvcResult result = mvc.perform(
				post("/nace/saveNaceDetails").contentType(MediaType.APPLICATION_JSON).content(asJsonString(nace)))
				.andExpect(status().isCreated()).andReturn();

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
