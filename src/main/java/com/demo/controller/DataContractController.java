package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.DataContract;
import com.demo.repository.DataContractRepository;

@RestController
@RequestMapping("/api/controller")
public class DataContractController {
	@Autowired
	private DataContractRepository dataContractRepository;
	
	@PostMapping("/contract/{id}")
	public ResponseEntity<List<DataContract>> addContract(
			@PathVariable(value = "id") Long id) {
		try {
			return new ResponseEntity<>(dataContractRepository.addContract(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/contract")
	public ResponseEntity<List<DataContract>> getContract() {
		try {
			return new ResponseEntity<>(dataContractRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/contract/{id}")
	public ResponseEntity<List<DataContract>> updateContract(
			@PathVariable(value = "id") Long id) {
		try {
			return new ResponseEntity<>(dataContractRepository.updateContract(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
