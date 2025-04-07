package com.example.poolpath.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/poolpath")
@RestController
public class RideController {

	@GetMapping("/rides")
	public List<String> getRides() {
		return List.of("Ride1", "Ride2", "Ride3");
	}
	
	@PostMapping("/rides")
	public String addRide(@RequestBody String rideName) {
		return rideName + " added";
	}
}
