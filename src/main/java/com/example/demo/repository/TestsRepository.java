package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tests;

@Repository
public interface TestsRepository extends JpaRepository<Tests, Long>{
//	List<Tests> findById(String id);
}