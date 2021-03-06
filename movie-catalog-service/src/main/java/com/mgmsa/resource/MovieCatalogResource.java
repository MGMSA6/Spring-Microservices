package com.mgmsa.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.mgmsa.model.CatalogItem;
import com.mgmsa.model.Movie;
import com.mgmsa.model.UserRating;

@RestController
@RequestMapping(value = { "/catalog" })
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	// For looping through client instances 
	@Autowired
	private DiscoveryClient DiscoveryClient;

	@RequestMapping(value = { "/{userId}" })
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
 
	    /*Without Eureka
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId,
				UserRating.class);   */
		
		
		 // With Eureka
		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId,
				UserRating.class);
				
		return ratings.getUserRating().stream().map(rating -> {

			// For each movie ID, call movie info services and get Details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

			// Put them all together
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());

		}).collect(Collectors.toList());

		// Asynchronous Api Call using WebClient

		/*
		  Movie movie = webClientBuilder.build() .get()
		  .uri("http://localhost:8082/movies/" + rating.getMovieId()) 
		  .retrieve()
		  .bodyToMono(Movie.class) .block();
		 */
	}
}
