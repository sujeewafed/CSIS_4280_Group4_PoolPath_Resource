package com.example.poolpath.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.poolpath.model.Request;
import com.example.poolpath.model.RequestRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RequestController {
	
	@Autowired
	RequestRepository requestRepository;
	
	// To get all requests, supporting search with rideId, requesterId
	@GetMapping("/requests")
	public ResponseEntity<List<Request>> getAllRides(@RequestParam(required = false) Long rideId,
			@RequestParam(required = false) Long requesterId) {  
	    try {
	        List<Request> requests = new ArrayList<Request>();

	        if (rideId == null && requesterId == null ) {
	        	requestRepository.findAll().forEach(requests::add);
	        } 
	        else if (rideId != null) {
	        	requestRepository.findByRideId(rideId).forEach(requests::add);
	        }
	        else if (requesterId != null) {
	        	requestRepository.findByRequesterId(requesterId).forEach(requests::add);
	        }
	        return new ResponseEntity<>(requests, HttpStatus.OK);

	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// To get a request
	@GetMapping("/requests/{id}")
	public ResponseEntity<Request> getRequestsById(@PathVariable("id") long id) {
		Optional<Request> requestData = requestRepository.findById(id);
		if (requestData.isPresent()) {
			return new ResponseEntity<>(requestData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// To create a new request
	@PostMapping("/requests")
	public ResponseEntity<Request> createRide(@RequestBody Request request) {
		try {
			Request _request = requestRepository.save(new Request(
					request.getRideId(), 
					request.getRideUserId(), 
					request.getStatus(),
					request.getRequesterId(), 
					request.getRequesterName(), 
					request.getMessage(), 
					request.getSeatsRequested())
					);
			return new ResponseEntity<>(_request, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// To update a request
	@PutMapping("/requests/{id}")
	public ResponseEntity<Request> updateRequest(@PathVariable("id") long id, @RequestBody Request request) {
		Optional<Request> requestData = requestRepository.findById(id);
		if (requestData.isPresent()) {
			Request _request = requestData.get();
			_request.setRideId(request.getRideId());
			_request.setRideUserId(request.getRideUserId());
			_request.setStatus(request.getStatus());
			_request.setRequesterId(request.getRequesterId());
			_request.setRequesterName(request.getRequesterName());
			_request.setMessage(request.getMessage());
			_request.setSeatsRequested(request.getSeatsRequested());
			return new ResponseEntity<>(requestRepository.save(_request), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// To delete a request
	@DeleteMapping("/request/{id}")
	public ResponseEntity<HttpStatus> deleteRequest(@PathVariable("id") long id) {
		try {
			requestRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
