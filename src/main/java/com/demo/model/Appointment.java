package com.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="date_app")
	private LocalDate date_app;
	@Column(name = "hour_app")
	private LocalTime hour_app;
	@Column(name = "id_test")
	private int id_test;
	@Column(name = "id_affiliate")
	private int id_affiliate;
	
	public Appointment() {
		super();
	}

	public Appointment(LocalDate date_app, LocalTime  hour_app, int id_test, int id_affiliate) {
		super();
		this.date_app = date_app;
		this.hour_app = hour_app;
		this.id_test = id_test;
		this.id_affiliate = id_affiliate;
	}

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

	public int getId_test() {
		return id_test;
	}

	public void setId_test(int id_test) {
		this.id_test = id_test;
	}

	public int getId_affiliate() {
		return id_affiliate;
	}

	public void setId_affiliate(int id_affiliate) {
		this.id_affiliate = id_affiliate;
	}

	@Override
	public String toString() {
		return "Appoinment [id=" + id + ", date_app=" + date_app + ", hour_app=" + hour_app + ", id_test=" + id_test
				+ ", id_affiliate=" + id_affiliate + "]";
	}
}
