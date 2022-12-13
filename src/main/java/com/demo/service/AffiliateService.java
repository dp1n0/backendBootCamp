package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.model.Affiliate;

public interface AffiliateService {
	List<Affiliate> getList();
	
	Optional<Affiliate> getById(Long id);
	
	Affiliate post(Affiliate affiliate);
	
	Affiliate put(Affiliate affiliate);
	
	void delete(Long id);
}
