package com.example.poolpath.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
	
	List<Ride> findByOriginAndDestinationAndDatetime(String origin, String destination, LocalDateTime datetime);
	List<Ride> findByOriginAndDestination(String origin, String destination);
	List<Ride> findByOrigin(String origin);
	List<Ride> findByDestination(String destination);
	List<Ride> findByDatetime(LocalDateTime datetime);
	Iterable<Ride> findByDatetimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
	Iterable<Ride> findByOriginAndDestinationAndDatetimeBetween(String origin, String destination,
			LocalDateTime startOfDay, LocalDateTime endOfDay);
	List<Ride> findByUserId(Long userId);
}
