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
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String imagePath;
	@ManyToOne(fetch = FetchType.EAGER)
	private City city;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel")
	private List<Image> images;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel")
	private List<HotelRoom> hotelRooms;
	
	public List<HotelRoom> getHotelRooms() {
		return hotelRooms;
	}
	public void setHotelRooms(List<HotelRoom> hotelRooms) {
		this.hotelRooms = hotelRooms;
	}
	public Hotel() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
}
