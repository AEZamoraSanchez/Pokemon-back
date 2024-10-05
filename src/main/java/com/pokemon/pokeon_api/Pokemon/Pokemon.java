package com.pokemon.pokeon_api.Pokemon;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;


@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private List<String> types;

    private Long weight;

    private Long hp;

    private Long attack;

    private Long defense;

    private Long speed;

    private Long height;
}
