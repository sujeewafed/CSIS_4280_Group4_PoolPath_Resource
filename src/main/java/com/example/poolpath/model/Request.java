package com.example.poolpath.model;

import java.time.LocalDateTime;

import com.example.poolpath.util.RequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "requests")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "rideId")
	private long rideId;
	
	@Column(name = "rideUserId")
	private long rideUserId;
	
	@Column(name = "status")
	private RequestStatus status;
	
	@Column(name = "requesterId")
	private long requesterId;
	
	@Column(name = "requesterName")
	private String requesterName;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "seatsRequested")
	private int seatsRequested;
	
	@Column(name = "createdDateTime")
	private LocalDateTime createdDateTime;
	
	public Request() {}

	public Request(long rideId, long rideUserId, RequestStatus status, long requesterId, String requesterName,
			String message, int seatsRequested) {
		super();
		this.rideId = rideId;
		this.rideUserId = rideUserId;
		this.status = status;
		this.requesterId = requesterId;
		this.requesterName = requesterName;
		this.message = message;
		this.seatsRequested = seatsRequested;
		this.createdDateTime = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRideId() {
		return rideId;
	}

	public void setRideId(long rideId) {
		this.rideId = rideId;
	}

	public long getRideUserId() {
		return rideUserId;
	}

	public void setRideUserId(long rideUserId) {
		this.rideUserId = rideUserId;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(long requesterId) {
		this.requesterId = requesterId;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSeatsRequested() {
		return seatsRequested;
	}

	public void setSeatsRequested(int seatsRequested) {
		this.seatsRequested = seatsRequested;
	}
}
