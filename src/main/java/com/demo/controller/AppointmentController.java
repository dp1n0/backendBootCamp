package com.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
import com.demo.service.AppointmentService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController()
@RequestMapping("/api/controller")
public class AppointmentController {
private AppointmentService appointmentService;
	
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@GetMapping("/appointment")
	public ResponseEntity<List<Appointment>> getList() {
		try {
			return new ResponseEntity<>(appointmentService.getList(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/appointment/{id}")
	public ResponseEntity<Appointment> getById(@PathVariable(value = "id") Long id) {
		Optional<Appointment> appointment = appointmentService.getById(id);

		if (appointment.isPresent()) {
			return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/appointment")
	public ResponseEntity<Appointment> post(@RequestBody Appointment appointment) {
//		appointmentRepository.addContract(appointment.getId());
		try {
//			Long idT = appointment.getIdTest().getId();
//			System.out.println(idT);
//			Tests test = testsRepository.findById(appointment.getIdTest());
//			appointment.set			
			return new ResponseEntity<>(appointmentService.post(appointment), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/appointment/{id}")
	public ResponseEntity<Appointment> put(@PathVariable(value = "id") Long id,
			@RequestBody Appointment appointmentDetails) {
		Optional<Appointment> _test = appointmentService.getById(id);
		
		if (_test.isPresent()) {
			Appointment appointment = _test.get();
			appointment.setDateA(appointmentDetails.getDateA());
			appointment.setHourA(appointmentDetails.getHourA());
			appointment.setIdAffiliate(appointmentDetails.getIdAffiliate());
			appointment.setIdTest(appointmentDetails.getIdTest());
			return new ResponseEntity<>(appointmentService.put(appointment), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@DeleteMapping("/appointment/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id) {
		try {
			appointmentService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/appointment/affiliate/{id_affiliate}")
	public ResponseEntity<List<Appointment>> getByAffiliatesId(@PathVariable(name = "id_affiliate") long id) {
		try {
//			List<Appointment> appointment = new ArrayList<Appointment>();
//			appointmentRepository.findAffiliateById(id).forEach(appointment::add);
			List<Appointment> appointment = appointmentService.getByAffiliatesId(id);			
			
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
//			List<Appointment> appointment = new ArrayList<Appointment>();
//			appointmentRepository.findAffiliateByDate(date).forEach(appointment::add);
			List<Appointment> appointment = appointmentService.getByAffiliatesDate(date);
			
			if (appointment.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(appointment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PutMapping("/{appointmentId}/affiliate/{affiliateId}")
//	Appointment enrrollAffiliateToSubject(
//			@PathVariable long appointmentId, 
//			@PathVariable long affiliateId) {
//		Appointment appointment = appointmentRepository.findById(appointmentId).get();
//		Affiliate affiliate = affiliateRepository.findById(affiliateId).get();
//		appointment.enroll(affiliate);
//		return appointmentRepository.save(appointment); 
//	}
}
