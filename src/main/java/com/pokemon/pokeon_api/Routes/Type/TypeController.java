package com.pokemon.pokeon_api.Routes.Type;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokeon_api.Reponses.Type.AllTypesResponse;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired private TypeService typeService;

    @GetMapping("/set-types")
    public CompletableFuture<AllTypesResponse> setTypes () {
        return this.typeService.setTypes();
    }

    @GetMapping("/{id}")
    public Type getTypeById (@PathVariable UUID id) {
        return this.typeService.getTypeById(id);
    }

    @GetMapping()
    public Iterable<Type> getTypes (){
        return this.typeService.getTypes();
    }

    @PostMapping()
    public Type saveType ( @RequestBody Type type ) {
        return this.typeService.saveType( type );
    }
}
