package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/controller")
public class AppointmentController {
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@GetMapping("/appointment")
	public List<Appointment> getList(){
		return appointmentRepository.findAll();
	}
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<Appointment> getById(@PathVariable(value = "id") Long id)
	throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + id));
		return ResponseEntity.ok().body(appointment);
	}
	
	@PostMapping("/appointment")
	public Appointment post(@RequestBody Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	@PutMapping("/appointment/{id}")
	public ResponseEntity<Appointment> put(@PathVariable(value = "id") Long id,
			@RequestBody Appointment appointmentDetails) throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + id));
		
		appointment.setDate_app(appointmentDetails.getDate_app());
		appointment.setHour_app(appointmentDetails.getHour_app());
		appointment.setId_affiliate(appointmentDetails.getId_affiliate());
		appointment.setId_test(appointmentDetails.getId_test());
		
		final Appointment updateTests = appointmentRepository.save(appointment);
		return ResponseEntity.ok(updateTests);
	}
	
	@DeleteMapping("/appointment/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + id));
		
		appointmentRepository.delete(appointment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
//	@GetMapping("/appointment/date")
//	public ResponseEntity<List<Appointment>> getByDate() {
//		try {
//			List<Appointment> appointments = appointmentRepository.findByDate();
//
//			if (appointments.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(appointments, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
