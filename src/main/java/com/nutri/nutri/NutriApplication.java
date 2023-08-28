package com.nutri.nutri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class NutriApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutriApplication.class, args);
	}

}
