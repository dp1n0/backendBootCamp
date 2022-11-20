package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.model.Affiliate;
import com.example.demo.repository.AfilliateRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/controller")
public class AffiliatesController {
	@Autowired
	private AfilliateRepository afilliateRepository;
	
	@GetMapping("/affiliates")
	public List<Affiliate> getList(){
		return afilliateRepository.findAll();
	}
	
	@GetMapping("/affiliates/{id}")
	public ResponseEntity<Affiliate> getById(@PathVariable(value = "id") Long affiliateId)
	throws ResourceNotFoundException {
		Affiliate affiliate = afilliateRepository.findById(affiliateId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + affiliateId));
		return ResponseEntity.ok().body(affiliate);
	}
	
	@PostMapping("/affiliates")
	public Affiliate post(@RequestBody Affiliate affiliate) {
		return afilliateRepository.save(affiliate);
	}
	
	@PutMapping("/affiliates/{id}")
	public ResponseEntity<Affiliate> put(@PathVariable(value = "id") Long affiliateId,
			@RequestBody Affiliate affiliateDetails) throws ResourceNotFoundException {
		Affiliate affiliate = afilliateRepository.findById(affiliateId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + affiliateId));
		
		affiliate.setName(affiliateDetails.getName());
		affiliate.setAge(affiliateDetails.getAge());
		affiliate.setMail(affiliateDetails.getMail());
		
		final Affiliate updateTests = afilliateRepository.save(affiliate);
		return ResponseEntity.ok(updateTests);
	}
	
	@DeleteMapping("/affiliates/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long affiliateId)
			throws ResourceNotFoundException {
		Affiliate affiliate = afilliateRepository.findById(affiliateId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + affiliateId));
		
		afilliateRepository.delete(affiliate);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
