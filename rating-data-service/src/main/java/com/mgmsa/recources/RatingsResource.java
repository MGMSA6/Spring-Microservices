package com.mgmsa.recources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mgmsa.model.Rating;
import com.mgmsa.model.UserRating;

@RestController
@RequestMapping(value = {"ratingsdata"})
public class RatingsResource {
	
	@Autowired
	private UserRating userRating;
	
	@RequestMapping(value = {"/{movieId}"}, method = RequestMethod.GET)
	public Rating getRating(@PathVariable("movieId") String movieId) {
		
		return new Rating(movieId, 4);
	}

	@RequestMapping(value = {"users/{userId}"}, method = RequestMethod.GET)
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		
		List<Rating> ratings = Arrays.asList(
				
				new Rating("1234", 4),
				new Rating("5678", 3)
				);
		
		userRating.setUserRating(ratings);
		return userRating;
	}
}
