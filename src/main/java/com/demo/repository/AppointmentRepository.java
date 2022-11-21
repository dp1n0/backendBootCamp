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
	@Query(value = "select * from Appointments where id_affiliate = ?3", nativeQuery = true)
	List<Appointment> findAfilliateById(long id_affiliate);
	@Query(value = "select * from appointments where date_app = to_date('?1')", nativeQuery = true)
	List<Appointment> finAffiliateByDate(String date);
//	List<Appointment> findAffiliateById(String id_affiliate);
//	List<Appointment> findAffiliateById(@Param("id_affiliate") int id_affiliate);
}
