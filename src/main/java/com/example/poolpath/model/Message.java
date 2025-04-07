package com.example.poolpath.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "senderId")
	private long senderId;
	
	@Column(name = "receiverId")
	private long receiverId;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "createdDateTime")
	private LocalDateTime createdDateTime;
	
	public Message() {}

	public Message(long senderId, long receiverId, String message) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
		this.createdDateTime = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
