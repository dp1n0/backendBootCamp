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
import com.demo.model.Affiliate;
import com.demo.repository.AffiliateRepository;

@WebMvcTest(AffiliatesController.class)
public class AffiliatesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AffiliateRepository repository;

	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	private Affiliate affiliate;

	@BeforeEach
	void setUp() {
		affiliate = new Affiliate();
		affiliate.setName("Santa");
		affiliate.setAge(50);
		affiliate.setMail("santa@gmail.com");
		
		List<Affiliate> list = new ArrayList<>();
		list.add(affiliate);
		
		Optional<Affiliate> optional = Optional.of(affiliate);
		
		Mockito.when(repository.findAll()).thenReturn(list);
		Mockito.when(repository.findById(1L)).thenReturn(optional);
		Mockito.when(repository.save(affiliate)).thenReturn(affiliate);
	}
	
	@org.junit.jupiter.api.Test
	public void getAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/affiliates")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
		
		//Exception
		Mockito.when(repository.findAll()).thenThrow(RuntimeException.class);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/affiliates")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@org.junit.jupiter.api.Test
	public void getById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/affiliates/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
		
		//Exeption
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/affiliates/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@org.junit.jupiter.api.Test
	public void post() throws Exception {
		Affiliate update = new Affiliate();
		affiliate.setName("Sofia");
		affiliate.setAge(40);
		affiliate.setMail("sofia@gmail.com");
		
		String content = writer.writeValueAsString(update);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/controller/affiliates")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
		.andExpect(MockMvcResultMatchers.status().isCreated());
		
	}
	
	@org.junit.jupiter.api.Test
	public void put() throws Exception {
		Affiliate update = new Affiliate();
		affiliate.setName("Sofia");
		affiliate.setAge(40);
		affiliate.setMail("sofia@gmail.com");
		
		Mockito.when(repository.save(update)).thenReturn(update);
		String content = writer.writeValueAsString(update);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.put("/api/controller/affiliates/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content);
		
		mockMvc.perform(builder)
		.andExpect(status().isCreated());
		
		//Exception
		MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders
				.put("/api/controller/affiliates/2")
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
				.delete("/api/controller/affiliates/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		//Exception
	}

}
