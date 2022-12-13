package com.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
//	List<Appointment> findByAffiliate(long id_affiliate);
	List<Appointment> findBydateA(LocalDate date);
	List<Appointment> findByidAffiliate(Long id);
	
	@Query(value = "select * from Appointments where id_affiliate = ?1", nativeQuery = true)
	List<Appointment> findAffiliateById(long id);
	
//	@Query(value = "select * from appointments where date_app = ?1", nativeQuery = true)
	@Query(value = "select *"
			+ "from appointments  "
			+ "where date_app = ?1 "
			+ "group by id_affiliate, id, date_app, hour_app, id_test "
			+ "order by id_affiliate", nativeQuery = true)
	List<Appointment> findAffiliateByDate(LocalDate date);
	
//	@Query(value = "select shema.appointment_seq.nextval from dual", nativeQuery = true)
//	public long getValSequence();
//	
//	@Query(value = "select id from appointments order by id desc fetch first 1 rows only;", nativeQuery = true)
//	public long getSeq();
}
