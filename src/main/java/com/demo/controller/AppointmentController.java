package com.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

import com.demo.exception.ResourceNotFoundException;
import com.demo.model.Appointment;
import com.demo.repository.AppointmentRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController()
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
		appointmentRepository.addContract(appointment.getId());
		try {
//			appointmentRepository.addContract((long) 227);
//			appointmentRepository.addContract(appointmentRepository.getValSequence());
//			System.out.println(appointmentRepository.getValSequence());
			appointmentRepository.addContract(appointment.getId());
		} catch (Exception e) {
			
		}
		System.out.println(appointment.getId_test());
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
	
//	@GetMapping("/appointment/affiliates")
//	public ResponseEntity<List<Appointment>> getAllTutorials(@RequestParam(required = false) int affiliate_id) {
//		try {
//			List<Appointment> appointments = new ArrayList<Appointment>();
//			
//			appointmentRepository.findAffiliateById(affiliate_id).forEach(appointments::add);
//
////			if (affiliate_id != null)
//				
//
//			if (appointments.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(appointments, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
//	@GetMapping("/appointment/affiliate/{id}")
//	public ResponseEntity<Appointment> getByAffiliateId(@PathVariable(value = "id") int id) {
//		Optional<Appointment> appointment = Optional.of(new Appointment());
//		Appointment appointmentResponse = new Appointment();
//		
//		appointment = Optional.ofNullable(appointmentRepository.findAffiliateById(id));
//				
//		if (appointment.isPresent()) {
//			appointmentResponse.setDate_app(appointment.get().getDate_app());
//			appointmentResponse.setHour_app(appointment.get().getHour_app());
//			appointmentResponse.setId_affiliate(appointment.get().getId_affiliate());
//			appointmentResponse.setId_test(appointment.get().getId_test());
//			return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
//	@GetMapping(path = "/country/{countryId}")
//    public ResponseEntity<CountryResponse> getCountryDetails(@PathVariable("countryId") String countryId) {
//        country = Optional.of(new Country());
//        countryResponse = new CountryResponse();
//
//        country = Optional.ofNullable(countryRepository.findCountryByIsoCode(countryId.toUpperCase()));
//
//        if (country.isPresent()) {
//            Period period = diferenciaEntreFechas.calculateYearsOfIndependency(country.get().getCountryIdependenceDate());
//            countryResponse.setCountryName(country.get().getCountryName());
//            countryResponse.setCapitalName(country.get().getCountryCapital());
//            countryResponse.setIndependenceDate(country.get().getCountryIdependenceDate());
//            countryResponse.setDayssOfIndependency(period.getDays());
//            countryResponse.setMonthsOfIndependency(period.getMonths());
//            countryResponse.setYearsOfIndependency(period.getYears());
//        }
//        return ResponseEntity.ok(countryResponse);
//    }
	
	
//	@GetMapping("appointment/affiliate/{id}")
//	public ResponseEntity<List<Appointment>> getByAffiliateId(@PathVariable(value = "id_affiliate") long id) throws ResourceNotFoundException {
//		if (!appointmentRepository.existsById(id)) {
//			throw new ResourceNotFoundException("Not found affiliate id :: " + id);
//		}
//		
//		List<Appointment> appointments = appointmentRepository.findByAffiliate(id);
//		return new ResponseEntity<>(appointments, HttpStatus.OK);
//	}
	
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
