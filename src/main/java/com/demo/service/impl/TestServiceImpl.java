package com.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.model.Tests;
import com.demo.repository.TestsRepository;
import com.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	private TestsRepository testsRepository;
	
	public TestServiceImpl(TestsRepository testsRepository) {
		this.testsRepository = testsRepository;
	}

	@Override
	public List<Tests> getList() {
		List<Tests> test = testsRepository.findAll();
		return test;
	}

	@Override
	public Optional<Tests> getById(Long id) {
		return testsRepository.findById(id);
	}

	@Override
	public Tests post(Tests test) {
		return testsRepository.save(test);
	}

	@Override
	public Tests put(Tests test) {
		return testsRepository.save(test);
	}

	@Override
	public void delete(Long id) {
		testsRepository.deleteById(id);
	}
}
