package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.model.DataContract;

public interface DataContractRepository extends JpaRepository<DataContract, Long> {
	@Query(value = "insert into data_contract "
			+ "select appointments.id, affiliates.name, tests.description, affiliates.mail, affiliates.age, appointments.date_app, appointments.hour_app "
			+ "from appointments, affiliates, tests "
			+ "where appointments.id_affiliate = affiliates.id "
			+ "and tests.id = appointments.id_test "
			+ "and appointments.id = ?1", nativeQuery = true)
//	List<Appointment> addContract(long id);
	List<DataContract> addContract(long id);
	
	@Query(value = "update data_contract \r\n"
			+ "set (id, name, description, mail, age, date_app, hour_app) =\r\n"
			+ "(select appointments.id, affiliates.name, \r\n"
			+ "tests.description, affiliates.mail, affiliates.age, appointments.date_app, \r\n"
			+ "appointments.hour_app \r\n"
			+ "from appointments, affiliates, tests\r\n"
			+ "where appointments.id_affiliate = affiliates.id\r\n"
			+ "and tests.id = appointments.id_test\r\n"
			+ "and appointments.id = ?1)\r\n"
			+ "where id = ?1;", nativeQuery = true)
	List<DataContract> updateContract(long id);
	
	public List<DataContract> findAllByOrderByIdAsc();
	
}
