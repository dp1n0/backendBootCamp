package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.demo.model.Affiliate;
import com.demo.repository.AfilliateRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/controller")
public class AffiliatesController {
	@Autowired
	private AfilliateRepository afilliateRepository;
	
	@GetMapping("/affiliates")
	public ResponseEntity<List<Affiliate>> getList() {
		try {
			return new ResponseEntity<>(afilliateRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/affiliates/{id}")
	public ResponseEntity<Affiliate> getById(@PathVariable(value = "id") Long id) {
		Optional<Affiliate> affiliate = afilliateRepository.findById(id);

		if (affiliate.isPresent()) {
			return new ResponseEntity<>(affiliate.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/affiliates")
	public ResponseEntity<Affiliate> post(@RequestBody Affiliate affiliate) {
		try {
			return new ResponseEntity<>(afilliateRepository.save(affiliate), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/affiliates/{id}")
	public ResponseEntity<Affiliate> put(@PathVariable(value = "id") Long id,
			@RequestBody Affiliate affiliateDetails) {
		Optional<Affiliate> _test = afilliateRepository.findById(id);
		
		if (_test.isPresent()) {
			Affiliate affiliate = _test.get();
			
			affiliate.setName(affiliateDetails.getName());
			affiliate.setAge(affiliateDetails.getAge());
			affiliate.setMail(affiliateDetails.getMail());
			return new ResponseEntity<>(afilliateRepository.save(affiliate), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/affiliates/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id) {
		try {
			afilliateRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
