package com.pokemon.pokeon_api.Type;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired private TypeService typeService;

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
        System.out.println("controller post");
        return this.typeService.saveType( type );
    }
}
