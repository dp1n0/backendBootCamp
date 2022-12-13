package com.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.demo.model.Tests;
import com.demo.service.TestService;

@WebMvcTest(TestsController.class)
public class TestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TestService repository;

	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	private Tests test;

	@BeforeEach
	void setUp() {
		test = new Tests();
		test.setDescription("BLOOD");
		test.setName("BLOOD TESTS");
		
		List<Tests> tests = new ArrayList<>();
		tests.add(test);
		
		Optional<Tests> optional = Optional.of(test);
		
		Mockito.when(repository.getList()).thenReturn(tests);
		Mockito.when(repository.getById(1L)).thenReturn(optional);
		Mockito.when(repository.post(test)).thenReturn(test);
	}
	
	@org.junit.jupiter.api.Test
	public void getAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/tests")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
		
		//Exception
		Mockito.when(repository.getList()).thenThrow(RuntimeException.class);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/tests")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@org.junit.jupiter.api.Test
	public void getById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/tests/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
		
		//Exeption
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/tests/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@org.junit.jupiter.api.Test
	public void post() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/controller/tests")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ " \"name\": \"BLOOD\",\r\n"
						+ " \"description\": \"BLOOD TEST\"\r\n"
						+ "}"))
		.andExpect(MockMvcResultMatchers.status().isCreated());
		
	}
	
	@org.junit.jupiter.api.Test
	public void put() throws Exception {
		Tests update = new Tests();
		update.setName("FLU");
		update.setDescription("FLU TEST");
		
		Mockito.when(repository.put(update)).thenReturn(update);
		String content = writer.writeValueAsString(update);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.put("/api/controller/tests/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content);
		
		mockMvc.perform(builder)
		.andExpect(status().isCreated());
		
		//Exception
		MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders
				.put("/api/controller/tests/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content);
		
		mockMvc.perform(builder2)
		.andExpect(status().isNotFound());
	}
	
	@org.junit.jupiter.api.Test
	public void delete() throws Exception {
		//first is find by Id, that is in the @BeforeEach function
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/controller/tests/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		//Exception
	}
}
