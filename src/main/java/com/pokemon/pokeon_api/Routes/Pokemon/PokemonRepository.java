package com.pokemon.pokeon_api.Routes.Pokemon;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<Pokemon, UUID>{

}
