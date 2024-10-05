package com.pokemon.pokeon_api.Type;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

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
        System.out.println("service post");
        Type saveType = this.typeRepository.save(type);

        return saveType;
    }
}
