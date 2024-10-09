package com.pokemon.pokeon_api.Reponses.Pokemon;

import java.util.List;

public class AllPokemonsResponse {
    private int count;
    private String next;
    private String previous;
    private List<PokemonResponse> results;
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getNext() {
        return next;
    }
    public void setNext(String next) {
        this.next = next;
    }
    public String getPrevious() {
        return previous;
    }
    public void setPrevious(String previous) {
        this.previous = previous;
    }
    public List<PokemonResponse> getResults() {
        return results;
    }
    public void setResults(List<PokemonResponse> results) {
        this.results = results;
    }
}
