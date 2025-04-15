package com.example.poolpath.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
	List<Request> findByRideId(long rideId);
	List<Request> findByRideUserId(long rideUserId);
	List<Request> findByRequesterId(long requesterId);
}