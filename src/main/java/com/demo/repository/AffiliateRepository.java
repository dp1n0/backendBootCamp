package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Affiliate;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Long>{

}
