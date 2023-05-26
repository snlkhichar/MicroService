package com.lcwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {
	
	@Autowired
	RatingService ratingService;

	@Test
	void contextLoads() {
	}

	
//	@Test
//	void createRating()
//	{
//		
//		com.lcwd.user.service.entities.Rating rating = new com.lcwd.user.service.entities.Rating(10,"","","this is created by feign client");
//		ResponseEntity<Rating> ratingResponseEntity = ratingService.createRating(rating);
//		System.out.println("new rating created");
//	}
}
