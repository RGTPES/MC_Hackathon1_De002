package org.example.genreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GenreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenreServiceApplication.class, args);
	}

}
