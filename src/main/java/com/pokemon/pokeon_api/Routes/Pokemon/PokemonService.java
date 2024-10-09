package com.pokemon.pokeon_api.Routes.Pokemon;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.pokeon_api.Reponses.Pokemon.AllPokemonsResponse;

@Service
public class PokemonService {

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://pokeapi.co/api/v2/pokemon?limit=10&offset=0"))
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

            System.out.println(futures);
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> response);
    }


    private CompletableFuture<Pokemon> getPokemonDetails(String url) {
        HttpRequest pokemonRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        return client.sendAsync(pokemonRequest, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(this::parsePokemon);
    }


    private Pokemon parsePokemon(String responseBody){
        try {
            return objectMapper.readValue(responseBody, Pokemon.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al parsear la respuesta", e);
        }
    }

    private Pokemon savePokemon( Pokemon pokemon){

        Pokemon newPokemon = new Pokemon();
        newPokemon.setName(pokemon.getName());
        newPokemon.setWeight(pokemon.getWeight());
        newPokemon.setHp(pokemon.getHp());
        newPokemon.setAttack(pokemon.getAttack());
        newPokemon.setDefense(pokemon.getDefense());
        newPokemon.setSpeed(pokemon.getSpeed());
        newPokemon.setHeight(pokemon.getHeight());

        return this.pokemonRepository.save(newPokemon);

        
    }

    public Iterable<Pokemon> getPokemons(){

        Iterable<Pokemon> pokemons = this.pokemonRepository.findAll();

        return pokemons;
    }


    
}
