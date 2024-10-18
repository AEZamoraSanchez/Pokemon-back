package com.pokemon.pokeon_api.Routes.Pokemon;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GenerationType;


@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    
    // @NotBlank(message = "Weight is mandatory")
    private Long weight;
    
    // @NotBlank(message = "Hp is mandatory")
    private Long hp;
    
    // @NotBlank(message = "Attack is mandatory")
    private Long attack;
    
    // @NotBlank(message = "Defense is mandatory")
    private Long defense;
    
    // @NotBlank(message = "Speed is mandatory")
    private Long speed;
    
    // @NotBlank(message = "Height is mandatory")
    private Long height;
    
    // @NotBlank(message = "Types is mandatory")
    private List<String> types;
    
    // @NotBlank(message = "Sprites is mandatory")
    private List<String> sprites;


    public List<String> getSprites() {
        return sprites;
    }
    
    public void setSprites(List<String> sprites) {
        this.sprites = sprites;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List
    <String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getHp() {
        return hp;
    }

    public void setHp(Long hp) {
        this.hp = hp;
    }

    public Long getAttack() {
        return attack;
    }

    public void setAttack(Long attack) {
        this.attack = attack;
    }

    public Long getDefense() {
        return defense;
    }

    public void setDefense(Long defense) {
        this.defense = defense;
    }

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}
