package com.skilldistillery.intersteller.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Mixer {
	
	@Id  
	@GeneratedValue( strategy =GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name="event_date")
	private LocalDate eventDate;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@Column(name="event_start")
	private LocalDate eventStart;	
	
	@Column(name="event_end")
	private LocalDate eventEnd;	
	
	@Column(name="created_date")
	private LocalDate createdDate;	
	
	@Column(name="image_url")
	private LocalDate imageUrl;
	
//	@OneToOne
//	@JoinColumn(name="profile_id")
//	private Profile profile;

	public Mixer() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDate getEventStart() {
		return eventStart;
	}

	public void setEventStart(LocalDate eventStart) {
		this.eventStart = eventStart;
	}

	public LocalDate getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(LocalDate eventEnd) {
		this.eventEnd = eventEnd;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(LocalDate imageUrl) {
		this.imageUrl = imageUrl;
	}	
	
	

}