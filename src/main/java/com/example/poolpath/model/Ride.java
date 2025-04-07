package com.example.poolpath.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rides")
public class Ride {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "origin")
	private String origin;
	
	@Column(name = "originAddress")
	private String originAddress;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "destinationAddress")
	private String destinationAddress;
	
	@Column(name = "seatsAvailable")
	private int seatsAvailable;
	
	@Column(name = "seatsCapacity")
	private int seatsCapacity;
	
	@Column(name = "isFull")
	private boolean isFull;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "datetime")
	private LocalDateTime datetime;
	
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "createdDateTime")
	private LocalDateTime createdDateTime;
	
	public Ride() {}

	public Ride(String origin, String originAddress, String destination, String destinationAddress, int seatsAvailable, int seatsCapacity, boolean isFull, String phoneNumber, LocalDateTime datetime,
			long userId) {
		this.origin = origin;
		this.originAddress = originAddress;
		this.destination = destination;
		this.destinationAddress = destinationAddress;
		this.seatsAvailable = seatsAvailable;
		this.seatsCapacity = seatsCapacity;
		this.isFull = isFull;
		this.phoneNumber = phoneNumber;
		this.datetime = datetime;
		this.userId = userId;
		this.createdDateTime = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getSeatsCapacity() {
		return seatsCapacity;
	}

	public void setSeatsCapacity(int seatsCapacity) {
		this.seatsCapacity = seatsCapacity;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public String getOriginAddress() {
		return originAddress;
	}

	public void setOriginAddress(String originAddress) {
		this.originAddress = originAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	
	
	
}
