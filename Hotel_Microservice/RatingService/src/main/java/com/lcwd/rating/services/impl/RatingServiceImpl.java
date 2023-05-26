package com.lcwd.rating.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository repository;

	@Override
	public Rating create(Rating rating) {
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return repository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		
		return repository.findAll();
	}

	//find rating using user id
	@Override
	public List<Rating> getRatingByUserId(String userId) {
		
		return repository.findByUserId(userId);
	}

	//find rating using hotel id
	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		
		return repository.findByHotelId(hotelId);
	}

	
}
