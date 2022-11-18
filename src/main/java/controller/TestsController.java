package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import model.Tests;
import repository.TestsRepository;

@RestController
@RequestMapping("api/v1")
public class TestsController {
	@Autowired
	private TestsRepository testsRepository;
	
	@GetMapping("/Tests")
	public List<Tests> getAllTests(){
		return testsRepository.findAll();
	}
	
	@GetMapping("Tests/{id}")
	public ResponseEntity<Tests> getTestsById(@PathVariable(value = "id") Long testId)
	throws ResourceNotFoundException {
		Tests test = testsRepository.findById(testId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + testId));
		return ResponseEntity.ok().body(test);
	}
	
	@PostMapping("/Tests")
	public Tests createTest(@RequestBody Tests test) {
		return testsRepository.save(test);
	}
	
	@PutMapping("/Tests/{id}")
	public ResponseEntity<Tests> updateTest(@PathVariable(value = "id") Long testId,
			@RequestBody Tests testDetails) throws ResourceNotFoundException {
		Tests test = testsRepository.findById(testId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + testId));
		
		test.setName(testDetails.getName());
		test.setDescription(testDetails.getDescription());
		
		final Tests updateTests = testsRepository.save(test);
		return ResponseEntity.ok(updateTests);
	}
	
	@DeleteMapping("/Tests/{id}")
	public Map<String, Boolean> deleteTest(@PathVariable(value = "id") Long testId)
			throws ResourceNotFoundException {
		Tests test = testsRepository.findById(testId)
				.orElseThrow(() -> new ResourceNotFoundException("Test not found for this id :: " + testId));
		
		testsRepository.delete(test);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
