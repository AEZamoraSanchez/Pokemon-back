package com.pokemon.pokeon_api.Routes.Type;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.pokeon_api.Reponses.Type.AllTypesResponse;


@Service
public class TypeService {

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://pokeapi.co/api/v2/type"))
        .build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TypeRepository typeRepository;

    
    public CompletableFuture<AllTypesResponse> setTypes() {
        
        return client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenApply(this::parseResponse)
        .thenApply(this::saveTypes);
        
    }
    
    private AllTypesResponse parseResponse(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, AllTypesResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al parsear la respuesta", e);
        }
    }
    
    private AllTypesResponse saveTypes ( AllTypesResponse response) {
        
        response.getResults().forEach(result -> {
            Type newType = new Type();
            newType.setName(result.getName());
            
            this.typeRepository.save(newType);
        });
        
        return response;
    }
    
    public Type getTypeById ( UUID id) {
        return this.typeRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Type Not Found"));

    }
    
    public Iterable<Type> getTypes (){

        Iterable<Type> types = typeRepository.findAll();

        // if (types.) {
        //     throw new RuntimeException("There aren't types");
        // }

        return types;
    }

    public Type saveType ( Type type) {
        Type saveType = this.typeRepository.save(type);

        return saveType;
    }
}
