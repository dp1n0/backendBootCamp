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
import com.example.demo.model.Tests;
import com.example.demo.repository.TestsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/controller")
public class TestsController {
	@Autowired
	private TestsRepository testsRepository;
	
	@GetMapping("/tests")
	public List<Tests> getList(){
		return testsRepository.findAll();
	}
	
	@GetMapping("tests/{id}")
	public ResponseEntity<Tests> getById(@PathVariable(value = "id") Long testId)
	throws ResourceNotFoundException {
		Tests test = testsRepository.findById(testId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + testId));
		return ResponseEntity.ok().body(test);
	}
	
	@PostMapping("/tests")
	public Tests post(@RequestBody Tests test) {
		return testsRepository.save(test);
	}
	
	@PutMapping("/tests/{id}")
	public ResponseEntity<Tests> put(@PathVariable(value = "id") Long testId,
			@RequestBody Tests testDetails) throws ResourceNotFoundException {
		Tests test = testsRepository.findById(testId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + testId));
		
		test.setName(testDetails.getName());
		test.setDescription(testDetails.getDescription());
		
		final Tests updateTests = testsRepository.save(test);
		return ResponseEntity.ok(updateTests);
	}
	
	@DeleteMapping("/tests/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long testId)
			throws ResourceNotFoundException {
		Tests test = testsRepository.findById(testId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + testId));
		
		testsRepository.delete(test);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
