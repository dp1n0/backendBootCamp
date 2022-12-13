package com.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.model.Affiliate;
import com.demo.repository.AffiliateRepository;
import com.demo.service.AffiliateService;

@Service
public class AffiliateServiceImpl implements AffiliateService {
	
	private AffiliateRepository affiliateRepository;
	
	public AffiliateServiceImpl(AffiliateRepository affiliateRepository) {
		this.affiliateRepository = affiliateRepository;
	}

	@Override
	public List<Affiliate> getList() {
		List<Affiliate> affiliate = affiliateRepository.findAll();
		return affiliate;
	}

	@Override
	public Optional<Affiliate> getById(Long id) {
		return affiliateRepository.findById(id);
	}

	@Override
	public Affiliate post(Affiliate affiliate) {
		return affiliateRepository.save(affiliate);
	}

	@Override
	public Affiliate put(Affiliate affiliate) {
		return affiliateRepository.save(affiliate);
	}

	@Override
	public void delete(Long id) {
		affiliateRepository.deleteById(id);
	}
}
