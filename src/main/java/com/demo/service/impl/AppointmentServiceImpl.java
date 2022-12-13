package com.demo.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.model.Appointment;
import com.demo.repository.AppointmentRepository;
import com.demo.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	private AppointmentRepository appointmentRepository;
	
	public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public List<Appointment> getList() {
		List<Appointment> appointment = appointmentRepository.findAll();
		return appointment;
	}

	@Override
	public Optional<Appointment> getById(Long id) {
		return appointmentRepository.findById(id);
	}

	@Override
	public Appointment post(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment put(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public void delete(Long id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public List<Appointment> getByAffiliatesId(Long id) {
//		return appointmentRepository.findByidAffiliate(id);
		return appointmentRepository.findAffiliateById(id);
	}

	@Override
	public List<Appointment> getByAffiliatesDate(LocalDate date) {
		return appointmentRepository.findBydateA(date);
	}
}
