package com.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.demo.model.Appointment;

public interface AppointmentService {
	List<Appointment> getList();
	
	Optional<Appointment> getById(Long id);
	
	Appointment post(Appointment appointment);
	
	Appointment put(Appointment appointment);
	
	void delete(Long id);
	
	List<Appointment> getByAffiliatesId(Long id);
	
	List<Appointment> getByAffiliatesDate(LocalDate date);
}
