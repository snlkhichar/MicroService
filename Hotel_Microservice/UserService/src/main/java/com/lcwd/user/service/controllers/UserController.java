package com.lcwd.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	//Create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	int retryCount=1;
	//Get Single user
	@GetMapping("/{userId}")
	//@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		System.out.println("retryCount :"+retryCount);
		retryCount++;
		User user=userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	 

	//creating fall back method for Circuitbreaker
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex)
	{
		System.out.println("fallback is executed beacuse service is down :"+ex.getMessage());
		
		User user = new User("dummy@gmail.com","dummy","This is user created dummy because some service is down","141234");
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	//All user
	@GetMapping
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> allUser=userService.getAlluser();
		return ResponseEntity.ok(allUser);
	}
}
