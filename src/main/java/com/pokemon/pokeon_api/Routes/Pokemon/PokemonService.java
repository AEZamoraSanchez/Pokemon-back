package com.pokemon.pokeon_api.Routes.Pokemon;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.pokeon_api.Exceptions.NotFoundException;
import com.pokemon.pokeon_api.Reponses.Pokemon.AllPokemonsResponse;
import com.pokemon.pokeon_api.Reponses.Pokemon.PokemonUrlResponse;

@Service
public class PokemonService {

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://pokeapi.co/api/v2/pokemon?limit=100&offset=0"))
        .build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PokemonRepository pokemonRepository;

    public CompletableFuture<AllPokemonsResponse> setPokemons() {
        return client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(this::parseResponse)
            .thenCompose(this::getAndSavePokemons);
    }

    private AllPokemonsResponse parseResponse(String responseBody ){
        try {
            return objectMapper.readValue(responseBody, AllPokemonsResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al parsear la respuesta", e);
        }
    }

    private CompletableFuture<AllPokemonsResponse> getAndSavePokemons( AllPokemonsResponse response){
        List<CompletableFuture<Object>> futures = response.getResults().stream()
            .map(result -> getPokemonDetails(result.getUrl())
                .thenApply(this::savePokemon)
                .thenApply(pokemon -> null))

            .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> response);
    }


    private CompletableFuture<PokemonUrlResponse> getPokemonDetails(String url) {

        HttpRequest pokemonRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        return client.sendAsync(pokemonRequest, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(this::parsePokemon);
    }


    private PokemonUrlResponse parsePokemon(String responseBody){
        try {

            PokemonUrlResponse pokemon = objectMapper.readValue(responseBody, PokemonUrlResponse.class);
            return pokemon;
        } catch (Exception e) {
            throw new RuntimeException("Error al parsear la respuesta", e);
        }
    }

    private Pokemon savePokemon( PokemonUrlResponse pokemon){

        Pokemon newPokemon = new Pokemon();
        String foo[] = {pokemon.getSprites().getBack_default(), pokemon.getSprites().getFront_default() };
        
        System.out.println(pokemon);
        System.out.println(pokemon.getTypes());
        List<String> sprites = Arrays.asList(foo);
        List<String> types = new ArrayList<>();

        for (PokemonUrlResponse.TypeSlot typeSlot : (Iterable<PokemonUrlResponse.TypeSlot>) pokemon.getTypes()){
            types.add(typeSlot.getType().getName());
        }
        
        
        newPokemon.setName(pokemon.getName());
        newPokemon.setWeight(pokemon.getWeight());
        newPokemon.setHeight(pokemon.getHeight());
        newPokemon.setSprites(sprites);
        newPokemon.setTypes(types);


        for ( PokemonUrlResponse.StatInfo statInfo : pokemon.getStats()){
            switch (statInfo.getStat().getName().toLowerCase()) {
                case "hp":
                    newPokemon.setHp((Long) statInfo.getBase_stat());
                    break;
                case "attack":
                    newPokemon.setAttack((Long) statInfo.getBase_stat());
                    break;
                case "defense":
                    newPokemon.setDefense((Long) statInfo.getBase_stat());
                    break;
                case "speed":
                    newPokemon.setSpeed((Long) statInfo.getBase_stat());
                    break;
        }
    }
        return this.pokemonRepository.save(newPokemon);

        
    }

    public Iterable<Pokemon> getPokemons(){

        Iterable<Pokemon> pokemons = this.pokemonRepository.findAll();

        if(pokemons.iterator().hasNext()){
            new NotFoundException("There is not Pokemons saved");
        }

        return pokemons;
    }

    public Pokemon getPokemonById (UUID id ) {

        Pokemon pokemonFound = this.pokemonRepository.findById(id).orElseThrow(() -> new NotFoundException("Pokemon with id: " + id + "not found") );

        return pokemonFound;
    }
    
    public Pokemon createPokemon ( Pokemon pokemon) {

        return this.pokemonRepository.save(pokemon);
    }

    public Pokemon updatePokemon ( UUID id, Pokemon pokemon){
        Pokemon pokemonToUpdate = this.pokemonRepository.findById(id).
            orElseThrow(() -> new NotFoundException("Pokemon to update not found"));

            if (pokemon.getName() != null) {
                pokemonToUpdate.setName(pokemon.getName());
            }
            if (pokemon.getWeight() != null) {
                pokemonToUpdate.setWeight(pokemon.getWeight());
            }
            if (pokemon.getHp() != null) {
                pokemonToUpdate.setHp(pokemon.getHp());
            }
            if (pokemon.getAttack() != null) {
                pokemonToUpdate.setAttack(pokemon.getAttack());
            }
            if (pokemon.getDefense() != null) {
                pokemonToUpdate.setDefense(pokemon.getDefense());
            }
            if (pokemon.getSpeed() != null) {
                pokemonToUpdate.setSpeed(pokemon.getSpeed());
            }
            if (pokemon.getHeight() != null) {
                pokemonToUpdate.setHeight(pokemon.getHeight());
            }
        
            return this.pokemonRepository.save(pokemonToUpdate);

    }

    public String deletePokemon (UUID id) {


        Pokemon pokemon = this.pokemonRepository.findById(id).
            orElseThrow(() -> new NotFoundException("Pokemon to delete not found"));

        this.pokemonRepository.delete(pokemon);

        return "Pokemon:" + pokemon.getName() + "deleted";

    }


    
}
