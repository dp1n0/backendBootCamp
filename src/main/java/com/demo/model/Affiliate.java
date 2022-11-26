package com.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "affiliates")
public class Affiliate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "mail")
	private String mail;
	
//	@ManyToMany(fetch = FetchType.EAGER, 
//			cascade = {
//					CascadeType.PERSIST,
//					CascadeType.MERGE
//			},
//			mappedBy = "affiliatesSet")
//	@JsonIgnore
//	Set<Appointment> appointments = new HashSet<>();

	public Affiliate() {
		super();
	}

	public Affiliate(long id, String name, int age, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.mail = mail;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Affiliate [id=" + id + ", name=" + name + ", age=" + age + ", mail=" + mail + "]";
	}

//	public Set<Appointment> getAppointments() {
//		return appointments;
//	}
}
