package com.pokemon.pokeon_api.Routes.Pokemon;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokeon_api.Reponses.Pokemon.AllPokemonsResponse;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired private PokemonService pokemonService;
    
    @GetMapping()
    public Iterable<Pokemon> getAllPokemons() {
        return this.pokemonService.getPokemons();
    }
    
    @GetMapping("/set-pokemons")
    public CompletableFuture<AllPokemonsResponse> setPokemons () {
        return this.pokemonService.setPokemons();
    }
    
}
