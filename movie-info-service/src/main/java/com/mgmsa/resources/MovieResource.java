package com.mgmsa.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mgmsa.model.Movie;

@RestController
@RequestMapping(value = {"/movies"})
public class MovieResource {
	
	@RequestMapping(value = {"/{movieId}"}, method = RequestMethod.GET)
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		return new Movie(movieId, "Test name");
	}
}
