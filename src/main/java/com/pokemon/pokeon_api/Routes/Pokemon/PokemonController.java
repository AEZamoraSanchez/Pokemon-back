package com.pokemon.pokeon_api.Routes.Pokemon;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokeon_api.Reponses.Pokemon.AllPokemonsResponse;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired private PokemonService pokemonService;
    
    @GetMapping()
    public Iterable<Pokemon> getAllPokemons() {
        // try {
            
            return this.pokemonService.getPokemons();
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
    }
    
    @GetMapping("/set-pokemons")
    public CompletableFuture<AllPokemonsResponse> setPokemons () {
        return this.pokemonService.setPokemons();
    }

    @GetMapping("/{id}")
    public Pokemon getPokemonByIs(@PathVariable UUID id) {
        return this.pokemonService.getPokemonById(id);
    }
    

    @PostMapping()
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return this.pokemonService.createPokemon(pokemon);
    }

    @PatchMapping("/{id}")
    public Pokemon updatePokemon(@PathVariable UUID id, @RequestBody Pokemon pokemon){
        return this.pokemonService.updatePokemon(id, pokemon);
    }

    @DeleteMapping("/{id}")
    public String deletePokemon(@PathVariable UUID id){
        return this.pokemonService.deletePokemon(id);
    }
    
    
}
