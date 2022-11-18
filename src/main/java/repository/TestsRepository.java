package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Tests;

@Repository
public interface TestsRepository extends JpaRepository<Tests, Long>{
	
}
