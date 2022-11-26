package com.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "appointments")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="date_app")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone =  "GMT -5")
	private LocalDate date_app;
	@Column(name = "hour_app")
	@DateTimeFormat(pattern = "hh:mm")
//	@JsonFormat(pattern = "hh:mm", timezone =  "GMT -5")
	private LocalTime hour_app;
	@Column(name = "id_test")
	private long id_test;
	@Column(name = "id_affiliate")
	private long id_affiliate;
	
//	@ManyToMany(fetch = FetchType.EAGER ,
//			cascade = {
//			CascadeType.PERSIST,
//			CascadeType.MERGE
//	})
//	@JoinTable(
//			name = "data_contract_1",
//			joinColumns = {@JoinColumn(name = "id_affiliate")},
//			inverseJoinColumns = {@JoinColumn(name = "id")})
//	private Set<Affiliate> affiliatesSet = new HashSet<>();
	
	public Appointment() {
		super();
	}

	public Appointment(LocalDate date_app, LocalTime  hour_app, long id_test, long id_affiliate) {
		super();
		this.date_app = date_app;
		this.hour_app = hour_app;
		this.id_test = id_test;
		this.id_affiliate = id_affiliate;
	}
	
//	public void setId(long id) {
//		this.id = id;
//	}

	public long getId() {
		return id;
	}
	
	public LocalDate getDate_app() {
		return date_app;
	}

	public void setDate_app(LocalDate date_app) {
		this.date_app = date_app;
	}

	public LocalTime getHour_app() {
		return hour_app;
	}

	public void setHour_app(LocalTime hour_app) {
		this.hour_app = hour_app;
	}

	public long getId_test() {
		return id_test;
	}

	public void setId_test(long id_test) {
		this.id_test = id_test;
	}

	public long getId_affiliate() {
		return id_affiliate;
	}

	public void setId_affiliate(long id_affiliate) {
		this.id_affiliate = id_affiliate;
	}

	@Override
	public String toString() {
		return "Appoinment [id=" + id + ", date_app=" + date_app + ", hour_app=" + hour_app + ", id_test=" + id_test
				+ ", id_affiliate=" + id_affiliate + "]";
	}

//	public Set<Affiliate> getAffiliates() {
//		return affiliatesSet;
//	}
//	
//	public void enroll (Affiliate affiliate) {
//		affiliatesSet.add(affiliate);
//	}
}
