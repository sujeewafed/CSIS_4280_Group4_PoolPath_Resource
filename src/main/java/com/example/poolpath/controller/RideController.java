package com.example.poolpath.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.poolpath.model.Ride;
import com.example.poolpath.model.RideRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RideController {

	@Autowired
	RideRepository rideRepository;

	// To get all rides, supporting search with origin, destination, date
	@GetMapping("/rides")
	public ResponseEntity<List<Ride>> getAllRides(@RequestParam(required = false) String origin,
	        @RequestParam(required = false) String destination,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,  // Added LocalDate for date-only input
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime) {  // Existing datetime input
	    try {
	        List<Ride> rides = new ArrayList<Ride>();

	        // If both date and datetime are null, return all rides
	        if (origin == null && destination == null && date == null && datetime == null) {
	            rideRepository.findAll().forEach(rides::add);
	        } 
	        // If all parameters are provided, filter by origin, destination, and datetime
	        else if (origin != null && destination != null && datetime != null) {
	            rideRepository.findByOriginAndDestinationAndDatetime(origin, destination, datetime).forEach(rides::add);
	        }
	        // If only date is provided, convert it to the start and end of the day and filter rides
	        else if (date != null && origin == null && destination == null && datetime == null) {
	            LocalDateTime startOfDay = date.atStartOfDay();
	            LocalDateTime endOfDay = date.atTime(23, 59, 59);
	            rideRepository.findByDatetimeBetween(startOfDay, endOfDay).forEach(rides::add);
	        }
	        // If datetime is provided, keep the existing logic
	        else if (datetime != null && origin == null && destination == null && date == null) {
	            rideRepository.findByDatetime(datetime).forEach(rides::add);
	        }
	        // Handle other combinations of parameters as required
	        else if (origin != null && destination != null && date != null) {
	            LocalDateTime startOfDay = date.atStartOfDay();
	            LocalDateTime endOfDay = date.atTime(23, 59, 59);
	            rideRepository.findByOriginAndDestinationAndDatetimeBetween(origin, destination, startOfDay, endOfDay).forEach(rides::add);
	        } 
	        // Other combinations like origin, destination, etc. can follow similarly
	        else {
	            if (origin != null && destination != null) {
	                rideRepository.findByOriginAndDestination(origin, destination).forEach(rides::add);
	            } else if (origin != null) {
	                rideRepository.findByOrigin(origin).forEach(rides::add);
	            } else if (destination != null) {
	                rideRepository.findByDestination(destination).forEach(rides::add);
	            }
	        }
	        return new ResponseEntity<>(rides, HttpStatus.OK);

	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// To get all rides created by user
	@GetMapping("/rides/user/{userId}")
	public ResponseEntity<List<Ride>> getRidesByUserId(@PathVariable Long userId) {
	    try {
	        List<Ride> rides = rideRepository.findByUserId(userId);
	        if (rides.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No rides found for the user
	        }
	        return new ResponseEntity<>(rides, HttpStatus.OK); // Return the rides found for the user
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle any unexpected errors
	    }
	}
	
	// To get a ride
	@GetMapping("/rides/{id}")
	public ResponseEntity<Ride> getRidesById(@PathVariable("id") long id) {
		Optional<Ride> rideData = rideRepository.findById(id);
		if (rideData.isPresent()) {
			return new ResponseEntity<>(rideData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// To create a new ride
	@PostMapping("/rides")
	public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
		try {
			Ride _ride = rideRepository.save(new Ride(ride.getOrigin(), ride.getOriginAddress(), ride.getDestination(),
					ride.getDestinationAddress(), ride.getSeatsAvailable(), ride.getSeatsCapacity(), ride.isFull(),
					ride.getPhoneNumber(), ride.getDatetime(), ride.getUserId()));
			return new ResponseEntity<>(_ride, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// To update a ride
	@PutMapping("/rides/{id}")
	public ResponseEntity<Ride> updateRide(@PathVariable("id") long id, @RequestBody Ride ride) {
		Optional<Ride> rideData = rideRepository.findById(id);
		if (rideData.isPresent()) {
			Ride _ride = rideData.get();
			_ride.setOrigin(ride.getOrigin());
			_ride.setOriginAddress(ride.getOriginAddress());
			_ride.setDestination(ride.getDestination());
			_ride.setDestinationAddress(ride.getDestinationAddress());
			_ride.setSeatsAvailable(ride.getSeatsAvailable());
			_ride.setSeatsCapacity(ride.getSeatsCapacity());
			_ride.setFull(ride.isFull());
			_ride.setPhoneNumber(ride.getPhoneNumber());
			_ride.setDatetime(ride.getDatetime());
			_ride.setUserId(ride.getUserId());
			return new ResponseEntity<>(rideRepository.save(_ride), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// To delete a ride
	@DeleteMapping("/rides/{id}")
	public ResponseEntity<HttpStatus> deleteRide(@PathVariable("id") long id) {
		try {
			rideRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
