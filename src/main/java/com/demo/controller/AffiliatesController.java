package com.demo.controller;

import java.util.List;

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

import com.demo.exception.ResourceNoContentException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.model.Affiliate;
import com.demo.service.AffiliateService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/controller")
public class AffiliatesController {
	private AffiliateService affiliateService;
	
	public AffiliatesController(AffiliateService affiliateService) {
		super();
		this.affiliateService = affiliateService;
	}

	@GetMapping("/affiliates")
	public ResponseEntity<List<Affiliate>> getList() {
		try {
			return new ResponseEntity<>(affiliateService.getList(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/affiliates/{id}")
	public ResponseEntity<Affiliate> getById(@PathVariable(value = "id") Long id) 
			throws ResourceNotFoundException {
		Affiliate affiliate = affiliateService.getById(id).
				orElseThrow(() -> new ResourceNotFoundException
						("Affiliate not found for this id :: " + id));
		return ResponseEntity.ok().body(affiliate);
	}
	
	@PostMapping("/affiliates")
	public ResponseEntity<Affiliate> post(@RequestBody Affiliate affiliate) {
		try {
			return new ResponseEntity<>(affiliateService.post(affiliate), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/affiliates/{id}")
	public ResponseEntity<Affiliate> put(@PathVariable(value = "id") Long id,
			@RequestBody Affiliate affiliateDetails) throws ResourceNotFoundException {
		Affiliate affiliate = affiliateService.getById(id).
				orElseThrow(() -> new ResourceNotFoundException
						("Affiliate not found for this id :: " + id));
		
		affiliate.setName(affiliateDetails.getName());
		affiliate.setAge(affiliateDetails.getAge());
		affiliate.setMail(affiliateDetails.getMail());
		
		return new ResponseEntity<>(affiliateService.put(affiliate), HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/affiliates/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id) 
			throws ResourceNoContentException {
		Affiliate affiliate = affiliateService.getById(id).orElseThrow(() -> new ResourceNoContentException
				("Affiliate no content for this id :: " + id));
		affiliateService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
