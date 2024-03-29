package com.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.time.LocalDate;
import java.time.LocalTime;
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

import com.demo.model.Appointment;
import com.demo.service.AppointmentService;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AppointmentService repository;

	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	private Appointment appointment;

	@BeforeEach
	void setUp() {
		appointment = new Appointment();
		appointment.setDateA(LocalDate.now());
		appointment.setHourA(LocalTime.now());
//		appointment.setIdAffiliate(Affiliate);
//		appointment.setIdTest(100);
		
		List<Appointment> list = new ArrayList<>();
		list.add(appointment);
		
		Optional<Appointment> optional = Optional.of(appointment);
		
		Mockito.when(repository.getList()).thenReturn(list);
		Mockito.when(repository.getById(1L)).thenReturn(optional);
		Mockito.when(repository.post(appointment)).thenReturn(appointment);
		Mockito.when(repository.getByAffiliatesId(1L)).thenReturn(list);
		Mockito.when(repository.getByAffiliatesDate(LocalDate.now())).thenReturn(list);
	}
	
	@org.junit.jupiter.api.Test
	public void getAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
		
		//Exception
		Mockito.when(repository.getList()).thenThrow(RuntimeException.class);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@org.junit.jupiter.api.Test
	public void getById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
		
		//Exeption
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@org.junit.jupiter.api.Test
	public void post() throws Exception {
//		Appointment update = new Appointment();
//		LocalDate date = LocalDate.of(2022, 11, 25);
//		LocalTime time = LocalTime.of(8, 0);
//		update.setDateA(date);
//		update.setHourA(time);
//		update.setIdAffiliate(2);
//		update.setIdTest(103);
//		
//		String content = writer.writeValueAsString(update);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/controller/appointment")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"date_app\": \"25/11/2022\",\r\n"
						+ "    \"hour_app\": \"20:00\",\r\n"
						+ "    \"id_test\": \"103\",\r\n"
						+ "    \"id_affiliate\": \"2\"\r\n"
						+ "}"))
		.andExpect(MockMvcResultMatchers.status().isCreated());
		
	}
	
	@org.junit.jupiter.api.Test
	public void put() throws Exception {
		Appointment update = new Appointment();
		update.setDateA(LocalDate.now());
		update.setHourA(LocalTime.now());
//		update.setIdAffiliate(2);
//		update.setIdTest(103);
		
		Mockito.when(repository.put(update)).thenReturn(update);
//		String content = writer.writeValueAsString(update);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.put("/api/controller/appointment/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"date_app\": \"25/11/2022\",\r\n"
						+ "    \"hour_app\": \"20:00\",\r\n"
						+ "    \"id_test\": \"103\",\r\n"
						+ "    \"id_affiliate\": \"2\"\r\n"
						+ "}");
		
		mockMvc.perform(builder)
		.andExpect(status().isCreated());
		
		//Exception
		MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders
				.put("/api/controller/appointment/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"date_app\": \"25/11/2022\",\r\n"
						+ "    \"hour_app\": \"20:00\",\r\n"
						+ "    \"id_test\": \"103\",\r\n"
						+ "    \"id_affiliate\": \"2\"\r\n"
						+ "}");
		
		mockMvc.perform(builder2)
		.andExpect(status().isNotFound());
	}
	
	@org.junit.jupiter.api.Test
	public void delete() throws Exception {
		//first is find by Id, that is in the @BeforeEach function
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/controller/appointment/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		//Exception
	}
	
	@org.junit.jupiter.api.Test
	public void getByAffiliatesId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment/affiliate/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
		
		//Exeption - Error server
		Mockito.when(repository.getByAffiliatesId(2L)).thenThrow(RuntimeException.class);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment/affiliate/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
		
		//No content - Empty
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment/affiliate/3")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@org.junit.jupiter.api.Test
	public void getByAffiliatesDate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment/date/2022-12-12")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
		
		//Exeption - Error server
		//The exception will generate when the user enter a invalid information, for example
		//date incomplete, o a text string
		Mockito.when(repository.getByAffiliatesDate(LocalDate.now())).thenThrow(RuntimeException.class);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/appointment/date/2022-12-12")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
		
		//No content - Empty
//		List<Appointment> list = new ArrayList<>();
//		Mockito.when(repository.findAffiliateByDate(LocalDate.now())).thenReturn(list);
//		mockMvc.perform(MockMvcRequestBuilders
//				.get("/api/controller/appointment/date/2022-12-12")
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
