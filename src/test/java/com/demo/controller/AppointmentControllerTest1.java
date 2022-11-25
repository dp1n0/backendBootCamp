package com.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.model.Appointment;
import com.demo.repository.AppointmentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AppointmentControllerTest1 {
	
	@Autowired
	AppointmentRepository appointmentRepository;

	private Appointment getTest() {
		Appointment appointment = new Appointment();
		appointment.setDate_app(null);
		appointment.setHour_app(null);
		appointment.setId_affiliate(202L);
		appointment.setId_test(106L);
		return appointment;
	}
	
	@DisplayName("getList")
	@org.junit.jupiter.api.Test
	public void testGetList() {
		Appointment appointment = getTest();
		appointmentRepository.save(appointment);
		List<Appointment> result = new ArrayList<>();
		appointmentRepository.findAll().forEach(e -> result.add(e));
		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	@DisplayName("getById")
	@Test
	public void testGetById() {
		Appointment appointment = getTest();
		appointmentRepository.save(appointment);
		Appointment result = appointmentRepository.findById(appointment.getId()).get();
		assertEquals(appointment.getId(), result.getId());
	}

	@DisplayName("post")
	@Test
	public void testPost() {
		Appointment appointment = getTest();
		appointmentRepository.save(appointment);
		Appointment result = appointmentRepository.findById(appointment.getId()).get();
		assertEquals(appointment.getId(), result.getId());
		assertEquals(appointment.getId_affiliate(), result.getId_affiliate());
	}

	@DisplayName("put")
	@Test
	public void testPut() {
		Appointment appointment = getTest();
		appointmentRepository.save(appointment);
		Appointment result = appointmentRepository.findById(appointment.getId()).get();
		assertEquals(appointment.getId(), result.getId());
		assertEquals(appointment.getId_test(), result.getId_test());
	}

	@DisplayName("delete")
	@Test
	public void testDelete() {
		Appointment appointment = getTest();
		appointmentRepository.save(appointment);
		appointmentRepository.deleteById(appointment.getId());
		List <Appointment> result = new ArrayList<>();
		appointmentRepository.findAll().forEach(e -> result.add(e));
		assertNotNull(result);
//		assertEquals(result.size(), 0);
	}
}
