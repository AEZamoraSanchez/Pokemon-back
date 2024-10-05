package com.pokemon.pokeon_api.Reponses.Type;

import java.util.List;

public class AllTypesResponse {
    private int count;
    private String next;
    private String previous;
    private List<TypeResponse> results;
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
    public List<TypeResponse> getResults() {
        return results;
    }
    public void setResults(List<TypeResponse> results) {
        this.results = results;
    }
}
