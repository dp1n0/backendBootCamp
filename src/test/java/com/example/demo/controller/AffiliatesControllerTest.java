package com.example.demo.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.model.Tests;
import com.demo.repository.TestsRepository;

@DataJpaTest
public class AffiliatesControllerTest {
	@Autowired
	private TestsRepository testRepository;
	
	private Tests getTest() {
		Tests test = new Tests();
		test.setDescription("TRIGLYCERIDES TEST");
		test.setName("TRIGLYCERIDES");
		return test;
	}
	
	@DisplayName("Junit test for Get List")
	@Test
	public void testGetList() {
		Tests test = getTest();
		testRepository.save(test);
		List<Tests> result = new ArrayList<>();
		testRepository.findAll().forEach(e -> result.add(e));
		assertEquals(result.size(), 6);
	}

	@DisplayName("Junit test for Get By Id")
	@Test
	public void testGetById() {
		Tests test = getTest();
		testRepository.save(test);
		Tests result = testRepository.findById(test.getId()).get();
		assertEquals(test.getId(), result.getId());
	}

	@DisplayName("Junit test for Post")
	@Test
	public void testPost() {
		Tests test = getTest();
		testRepository.save(test);
		Tests result = testRepository.findById(test.getId()).get();
		assertEquals(test.getId(), result.getId());
	}

	@DisplayName("Junit test for Put")
	@Test
	public void testPut() {
		Tests test = getTest();
		testRepository.save(test);
		Tests result = testRepository.findById(test.getId()).get();
		assertEquals(test.getId(), result.getId());
	}

	@DisplayName("Junit test for Delete")
	@Test
	public void testDelete() {
		Tests test = getTest();
		testRepository.save(test);
		testRepository.deleteById(test.getId());
		List <Tests> result = new ArrayList<>();
		testRepository.findAll().forEach(e -> result.add(e));
		assertEquals(result.size(), 0);
	}
}
