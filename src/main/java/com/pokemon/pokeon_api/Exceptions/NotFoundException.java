package com.pokemon.pokeon_api.Exceptions;

public class NotFoundException extends RuntimeException{
    
    public NotFoundException(String message){
        super(message);
    }
}
