package com.demo.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.demo.model.Appointment;
import com.demo.model.DataContract;
import com.demo.repository.AppointmentRepository;
import com.demo.repository.DataContractRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@WebMvcTest(DataContractController.class)
public class DataContractTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DataContractRepository repository;
	@MockBean
	private AppointmentRepository appointmentRepository;

	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	private DataContract contract;

	@BeforeEach
	void setUp() {
		contract = new DataContract();
		contract.setName(null);
		contract.setDescription(null);
		contract.setEmail(null);
		contract.setAge(0);
		contract.setDate_app(LocalDate.now());
		contract.setHour_app(LocalTime.now());
		
		List<DataContract> list = new ArrayList<>();
		list.add(contract);
		
		Appointment appointment = new Appointment();
		appointment.setDate_app(LocalDate.now());
		appointment.setHour_app(LocalTime.now());
		appointment.setId_affiliate(1);
		appointment.setId_test(100);
		
		Optional<Appointment> optional = Optional.of(appointment);
		
		Mockito.when(repository.findAllByOrderByIdAsc()).thenReturn(list);
		Mockito.when(repository.addContract(1L)).thenReturn(list);
		Mockito.when(repository.updateContract(1L)).thenReturn(list);
		Mockito.when(appointmentRepository.findById(1L)).thenReturn(optional);
	}
	
	@org.junit.jupiter.api.Test
	public void getContract() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/contract")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
		
		//Exception
		Mockito.when(repository.findAllByOrderByIdAsc()).thenThrow(RuntimeException.class);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/controller/contract")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@org.junit.jupiter.api.Test
	public void addContract() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/controller/contract/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"id\": 283,\r\n"
						+ "    \"name\": \"Toby\",\r\n"
						+ "    \"description\": \"BLOOD TESTS 1\",\r\n"
						+ "    \"email\": \"toby@gmail.com\",\r\n"
						+ "    \"age\": 30,\r\n"
						+ "    \"date_app\": \"21/12/2022\",\r\n"
						+ "    \"hour_app\": \"08:00:00\"\r\n"
						+ "}"))
		.andExpect(jsonPath("$", hasSize(1)));
	}
	
	@org.junit.jupiter.api.Test
	public void updateContract() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.put("/api/controller/contract/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{{\r\n"
						+ "    \"id\": 283,\r\n"
						+ "    \"name\": \"Toby\",\r\n"
						+ "    \"description\": \"BLOOD TESTS 1\",\r\n"
						+ "    \"email\": \"toby@gmail.com\",\r\n"
						+ "    \"age\": 30,\r\n"
						+ "    \"date_app\": \"21/12/2022\",\r\n"
						+ "    \"hour_app\": \"08:00:00\"\r\n"
						+ "}");
		
		mockMvc.perform(builder)
		.andExpect(status().isOk());
		
		//Exception
		Mockito.when(repository.updateContract(10L)).thenThrow(RuntimeException.class);
		MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders
				.put("/api/controller/contract/10")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"id\": 283,\r\n"
						+ "    \"name\": \"Toby\",\r\n"
						+ "    \"description\": \"BLOOD TESTS 1\",\r\n"
						+ "    \"email\": \"toby@gmail.com\",\r\n"
						+ "    \"age\": 30,\r\n"
						+ "    \"date_app\": \"21/12/2022\",\r\n"
						+ "    \"hour_app\": \"08:00:00\"\r\n"
						+ "}");
		
		mockMvc.perform(builder2)
		.andExpect(status().isNotFound());
	}
}
