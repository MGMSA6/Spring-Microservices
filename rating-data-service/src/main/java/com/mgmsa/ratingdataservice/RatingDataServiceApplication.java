package com.mgmsa.ratingdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.mgmsa.model.UserRating;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.mgmsa.ratingdataservice","com.mgmsa.model","com.mgmsa.recources"})
public class RatingDataServiceApplication {
	
	@Bean
	public UserRating getUserRating() {
		return new UserRating();
	}

	public static void main(String[] args) {
		SpringApplication.run(RatingDataServiceApplication.class, args);
	}

}
