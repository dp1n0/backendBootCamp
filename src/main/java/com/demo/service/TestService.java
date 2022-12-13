package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.model.Tests;

public interface TestService {
	List<Tests> getList();
	
	Optional<Tests> getById(Long id);
	
	Tests post(Tests test);
	
	Tests put(Tests test);
	
	void delete(Long id);
}
