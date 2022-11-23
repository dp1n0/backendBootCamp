package com.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

import com.demo.model.Appointment;
import com.demo.repository.AppointmentRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController()
@RequestMapping("/api/controller")
public class AppointmentController {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@GetMapping("/appointment")
	public ResponseEntity<List<Appointment>> getList() {
		try {
			return new ResponseEntity<>(appointmentRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<Appointment> getById(@PathVariable(value = "id") Long id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);

		if (appointment.isPresent()) {
			return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/appointment")
	public ResponseEntity<Appointment> post(@RequestBody Appointment appointment) {
		appointmentRepository.addContract(appointment.getId());
		try {
			try {
//				appointmentRepository.addContract((long) 227);
//				appointmentRepository.addContract(appointmentRepository.getValSequence());
//				System.out.println(appointmentRepository.getValSequence());
				appointmentRepository.addContract(appointment.getId());
			} catch (Exception e) {
				
			}
			return new ResponseEntity<>(appointmentRepository.save(appointment), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/appointment/{id}")
	public ResponseEntity<Appointment> put(@PathVariable(value = "id") Long id,
			@RequestBody Appointment appointmentDetails) {
		Optional<Appointment> _test = appointmentRepository.findById(id);
		
		if (_test.isPresent()) {
			Appointment appointment = _test.get();
			appointment.setDate_app(appointmentDetails.getDate_app());
			appointment.setHour_app(appointmentDetails.getHour_app());
			appointment.setId_affiliate(appointmentDetails.getId_affiliate());
			appointment.setId_test(appointmentDetails.getId_test());
			return new ResponseEntity<>(appointmentRepository.save(appointment), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@DeleteMapping("/appointment/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id) {
		try {
			appointmentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/appointment/affiliate/{id_affiliate}")
	public ResponseEntity<List<Appointment>> getByAffiliatesId(@PathVariable(name = "id_affiliate") long id) {
		try {
			
			List<Appointment> appointment = new ArrayList<Appointment>();
			appointmentRepository.findAffiliateById(id).forEach(appointment::add);
			
			if (appointment.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(appointment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/appointment/date/{date}")
	public ResponseEntity<List<Appointment>> getByAffiliatesDate(@PathVariable(name = "date")	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		try {
			List<Appointment> appointment = new ArrayList<Appointment>();
			appointmentRepository.findAffiliateByDate(date).forEach(appointment::add);
			
			if (appointment.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(appointment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
