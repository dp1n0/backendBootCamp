package com.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.demo.model.Tests;
import com.demo.service.TestService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/controller")
public class TestsController {
private TestService testService;
	
	public TestsController(TestService testService) {
		super();
		this.testService = testService;
	}

	@GetMapping("/tests")
	public ResponseEntity<List<Tests>> getList() {
		try {
			return new ResponseEntity<>(testService.getList(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/tests/{id}")
	public ResponseEntity<Tests> getById(@PathVariable(value = "id") Long id) {
		Optional<Tests> test = testService.getById(id);

		if (test.isPresent()) {
			return new ResponseEntity<>(test.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/tests")
	public ResponseEntity<Tests> post(@RequestBody Tests test) {
		try {
			return new ResponseEntity<>(testService.post(test), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/tests/{id}")
	public ResponseEntity<Tests> put(@PathVariable(value = "id") Long id,
			@RequestBody Tests testDetails) {
		Optional<Tests> _test = testService.getById(id);
		
		if (_test.isPresent()) {
			Tests test = _test.get();
			test.setName(testDetails.getName());
			test.setDescription(testDetails.getDescription());
			return new ResponseEntity<>(testService.put(test), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/tests/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id) {
		try {
			testService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
