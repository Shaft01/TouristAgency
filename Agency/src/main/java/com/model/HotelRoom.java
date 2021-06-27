package com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class HotelRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Room room;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Hotel hotel;
	@Column()
	private Double pricePerPerson;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotelRoom")
	private List<Arrangement> arrangements;
	
	public HotelRoom() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Double getPricePerPerson() {
		return pricePerPerson;
	}
	public void setPricePerPerson(Double pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}


	public List<Arrangement> getArrangement() {
		return arrangements;
	}

	public void setArrangement(List<Arrangement> arrangement) {
		this.arrangements = arrangement;
	}
}
