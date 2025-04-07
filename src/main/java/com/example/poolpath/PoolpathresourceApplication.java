package com.example.poolpath;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.poolpath.model.User;
import com.example.poolpath.model.Ride;
import com.example.poolpath.model.RideRepository;
import com.example.poolpath.model.UserRepository;

@SpringBootApplication
public class PoolpathresourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoolpathresourceApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(
			UserRepository userRepository, 
			RideRepository rideRepository) {
		return args -> {
			loadData(userRepository, rideRepository);
		};
	}
	
	private void loadData(UserRepository userRepository, 
			RideRepository rideRepository) {
		
		// Adding users 
		ArrayList<User> users = new ArrayList<>();
		users.add(new User("user1", "user1", "User1", "7782236958"));
		users.add(new User("user2", "user2", "User2", "7788753648"));
		userRepository.saveAll(users);
		
		// Adding rides 
		ArrayList<Ride> rides = new ArrayList<>();
		rides.add(new Ride("BC Place", "BC Place", "Douglas College", "Douglas College New west", 20, 20, false, "7789962256",  LocalDateTime.parse("2025-04-07T10:00:00"), 1));
		rides.add(new Ride("Royal BC Museum", "Royal BC Museum", "Vancouver", "Vancouver burrard station", 30, 30, false, "77825639878",  LocalDateTime.parse("2025-04-10T10:00:00"), 1));
		rideRepository.saveAll(rides);
	}
}
