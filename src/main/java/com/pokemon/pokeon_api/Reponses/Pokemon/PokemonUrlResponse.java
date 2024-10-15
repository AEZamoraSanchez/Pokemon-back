package com.pokemon.pokeon_api.Reponses.Pokemon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemon.pokeon_api.Routes.Type.Type;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonUrlResponse {

    private String name;

    private Long weight;

    private Long height;
    
    private Iterable<TypeSlot> types;

    private Iterable<StatInfo> stats;

    private Sprites sprites;

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Iterable<TypeSlot> getTypes() {
        return types;
    }

    public void setTypes(Iterable<TypeSlot> types) {
        this.types = types;
    }

    public Iterable<StatInfo> getStats() {
        return stats;
    }

    public void setStats(Iterable<StatInfo> stats) {
        this.stats = stats;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprites {
        private String front_default;

        private String back_default;

        public String getBack_default() {
            return back_default;
        }

        public void setBack_default(String back_default) {
            this.back_default = back_default;
        }

        public String getFront_default() {
            return front_default;
        }

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }
    } 

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatInfo {
        private long base_stat;
        private Stat stat;
        public long getBase_stat() {
            return base_stat;
        }
        public void setBase_stat(long base_stat) {
            this.base_stat = base_stat;
        }
        public Stat getStat() {
            return stat;
        }
        public void setStat(Stat stat) {
            this.stat = stat;
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Stat {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TypeSlot {
        private long slot;
        private Type type;
        public long getSlot() {
            return slot;
        }
        public void setSlot(long slot) {
            this.slot = slot;
        }
        public Type getType() {
            return type;
        }
        public void setType(Type type) {
            this.type = type;
        }
    }

}

