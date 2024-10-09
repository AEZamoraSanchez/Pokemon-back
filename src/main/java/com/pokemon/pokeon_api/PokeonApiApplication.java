package com.pokemon.pokeon_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@SpringBootApplication
public class PokeonApiApplication {

	@RequestMapping("/")
	public String home () {
		return "Pokemon api";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PokeonApiApplication.class, args);
	}

}
