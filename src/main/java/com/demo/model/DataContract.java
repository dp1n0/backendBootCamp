package com.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "data_contract")
public class DataContract {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "name")
	private String name;
	private String description;
	@Column(name = "mail")
	private String email;
	@Column(name = "age")
	private int age;
	@Column(name="date_app")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone =  "GMT -5")
	private LocalDate date_app;
	@Column(name = "hour_app")
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime hour_app;
	
	public DataContract() {
		super();
	}
	
	public DataContract(String name, String description, String email, int age, LocalDate date_app,
			LocalTime hour_app) {
		super();
		this.name = name;
		this.description = description;
		this.email = email;
		this.age = age;
		this.date_app = date_app;
		this.hour_app = hour_app;
	}
	
	public long getId() {
		return id;
	}
//	public void setId(long id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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

	@Override
	public String toString() {
		return "DataContract [id=" + id + ", name=" + name + ", description=" + description + ", email=" + email
				+ ", age=" + age + ", date_app=" + date_app + ", hour_app=" + hour_app + "]";
	}

}
