package com.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
//	List<Appointment> findByDate(String _date);
//	List<Appointment> findByAffiliate(long id_affiliate);
	@Query(value = "select * from Appointments where id_affiliate = ?1", nativeQuery = true)
	List<Appointment> findAffiliateById(long id);
	
//	@Query(value = "select * from appointments where date_app = ?1", nativeQuery = true)
	@Query(value = "select *"
			+ "from appointments  "
			+ "where date_app = ?1 "
			+ "group by id_affiliate, id, date_app, hour_app, id_test "
			+ "order by id_affiliate", nativeQuery = true)
	List<Appointment> findAffiliateByDate(LocalDate date);
	
//	@Query(value = "insert into data_contract "
//			+ "select a.id, af.name, t.description, af.mail, af.age, a.date_app, a.hour_app "
//			+ "from appointments a, affiliates af, tests t "
//			+ "where a.id_affiliate = af.id "
//			+ "and t.id = a.id_test "
//			+ "and a.id = ?1", nativeQuery = true)
	@Query(value = "insert into data_contract "
			+ "select appointments.id, affiliates.name, tests.description, affiliates.mail, affiliates.age, appointments.date_app, appointments.hour_app "
			+ "from appointments, affiliates, tests "
			+ "where appointments.id_affiliate = affiliates.id "
			+ "and tests.id = appointments.id_test "
			+ "and appointments.id = ?1", nativeQuery = true)
	List<Appointment> addContract(long id);
	
	@Query(value = "select shema.appointment_seq.nextval from dual", nativeQuery = true)
	public long getValSequence();
//	List<Appointment> findAffiliateById(String id_affiliate);
//	List<Appointment> findAffiliateById(@Param("id_affiliate") int id_affiliate);
}
