package com.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "appointments")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="date_app")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone =  "GMT -5")
	private LocalDate dateA;
	@Column(name = "hour_app")
	@DateTimeFormat(pattern = "hh:mm")
//	@JsonFormat(pattern = "hh:mm", timezone =  "GMT -5")
	private LocalTime hourA;
	@JoinColumn(name = "id_test")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties
	@OneToOne
	private Tests idTest;
	@JoinColumn(name = "id_affiliate")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties
	@OneToOne
	private Affiliate idAffiliate;
	
	public Appointment() {
	}

	public Appointment(LocalDate dateA, LocalTime hourA, Tests idTest, Affiliate idAffiliate) {
		this.dateA = dateA;
		this.hourA = hourA;
		this.idTest = idTest;
		this.idAffiliate = idAffiliate;
	}

	public LocalDate getDateA() {
		return dateA;
	}

	public void setDateA(LocalDate dateA) {
		this.dateA = dateA;
	}

	public LocalTime getHourA() {
		return hourA;
	}

	public void setHourA(LocalTime hourA) {
		this.hourA = hourA;
	}

	public Tests getIdTest() {
		return idTest;
	}

	public void setIdTest(Tests idTest) {
		this.idTest = idTest;
	}

	public Affiliate getIdAffiliate() {
		return idAffiliate;
	}

	public void setIdAffiliate(Affiliate idAffiliate) {
		this.idAffiliate = idAffiliate;
	}

	public long getId() {
		return id;
	}	
}
