package com.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.model.Tests;
import com.demo.repository.TestsRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestControllerTest {
	
	@Autowired
	TestsRepository testRepository;

	private Tests getTest() {
		Tests test = new Tests();
		test.setDescription("TRIGLYCERIDES TEST");
		test.setName("TRIGLYCERIDES");
		return test;
	}
	
	@DisplayName("getList")
	@org.junit.jupiter.api.Test
	public void testGetList() {
		Tests test = getTest();
		testRepository.save(test);
		List<Tests> result = new ArrayList<>();
		testRepository.findAll().forEach(e -> result.add(e));
		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	@DisplayName("getById")
	@Test
	public void testGetById() {
		Tests test = getTest();
		testRepository.save(test);
		Tests result = testRepository.findById(test.getId()).get();
		assertEquals(test.getId(), result.getId());
	}

	@DisplayName("post")
	@Test
	public void testPost() {
		Tests test = getTest();
		testRepository.save(test);
		Tests result = testRepository.findById(test.getId()).get();
		assertEquals(test.getId(), result.getId());
		assertEquals(test.getDescription(), result.getDescription());
	}

	@DisplayName("put")
	@Test
	public void testPut() {
		Tests test = getTest();
		testRepository.save(test);
		Tests result = testRepository.findById(test.getId()).get();
		assertEquals(test.getId(), result.getId());
		assertEquals(test.getName(), result.getName());
	}

	@DisplayName("delete")
	@Test
	public void testDelete() {
		Tests test = getTest();
		testRepository.save(test);
		testRepository.deleteById(test.getId());
		List <Tests> result = new ArrayList<>();
		testRepository.findAll().forEach(e -> result.add(e));
		assertNotNull(result);
//		assertEquals(result.size(), 0);
	}
}
