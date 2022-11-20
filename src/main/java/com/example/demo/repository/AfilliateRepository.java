package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Affiliate;

@Repository
public interface AfilliateRepository extends JpaRepository<Affiliate, Long>{

}
