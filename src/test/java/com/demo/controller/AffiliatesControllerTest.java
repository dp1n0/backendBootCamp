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

import com.demo.model.Affiliate;
import com.demo.repository.AfilliateRepository;
import org.springframework.web.bind.annotation.GetMapping;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AffiliatesControllerTest {
	
	@Autowired
	AfilliateRepository testRepository;

	private Affiliate getAffiliate() {
		Affiliate affiliate = new Affiliate();
		affiliate.setAge(50);
		affiliate.setMail("Santa@gmail.com");
		affiliate.setName("Santa");
		return affiliate;
	}
	
	@DisplayName("getList")
	@GetMapping("/affiliates")
	@org.junit.jupiter.api.Test
	public void testGetList() {
		Affiliate affiliate = getAffiliate();
		testRepository.save(affiliate);
		List<Affiliate> result = new ArrayList<>();
		testRepository.findAll().forEach(e -> result.add(e));
		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	@DisplayName("getById")
	@Test
	public void testGetById() {
		Affiliate affiliate = getAffiliate();
		testRepository.save(affiliate);
		Affiliate result = testRepository.findById(affiliate.getId()).get();
		assertEquals(affiliate.getId(), result.getId());
	}

	@DisplayName("post")
	@Test
	public void testPost() {
		Affiliate affiliate = getAffiliate();
		testRepository.save(affiliate);
		Affiliate result = testRepository.findById(affiliate.getId()).get();
		assertEquals(affiliate.getId(), result.getId());
		assertEquals(affiliate.getAge(), result.getAge());
	}

	@DisplayName("put")
	@Test
	public void testPut() {
		Affiliate affiliate = getAffiliate();
		testRepository.save(affiliate);
		Affiliate result = testRepository.findById(affiliate.getId()).get();
		assertEquals(affiliate.getId(), result.getId());
		assertEquals(affiliate.getName(), result.getName());
	}

	@DisplayName("delete")
	@Test
	public void testDelete() {
		Affiliate affiliate = getAffiliate();
		testRepository.save(affiliate);
		testRepository.deleteById(affiliate.getId());
		List <Affiliate> result = new ArrayList<>();
		testRepository.findAll().forEach(e -> result.add(e));
		assertNotNull(result);
//		assertEquals(result.size(), 0);
	}
}
