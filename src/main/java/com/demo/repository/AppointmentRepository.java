package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
//	List<Appointment> findByDate(String _date);
//	List<Appointment> findByAffiliate(long id_affiliate);
	@Query("select a from Appointments a where a.id_affiliate = :id_affiliate")
	List<Appointment> findAfilliateById(@Param("id_affiliate") int id_affiliate);
//	List<Appointment> findAffiliateById(String id_affiliate);
//	List<Appointment> findAffiliateById(@Param("id_affiliate") int id_affiliate);
}
