package com.pokemon.pokeon_api;

import org.springframework.boot.SpringApplication;

public class TestPokeonApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(PokeonApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
