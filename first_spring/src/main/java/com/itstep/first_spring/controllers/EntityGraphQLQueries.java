package com.itstep.first_spring.controllers;

import com.itstep.first_spring.models.EntityModel;
import com.itstep.first_spring.services.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class EntityGraphQLQueries {

    private final EntityService entityService;

    @QueryMapping
    public List<EntityModel> getEntities() {
        List<EntityModel> res = entityService.findAll().stream().toList();
        return res;
    }

    @QueryMapping
    public EntityModel getEntityById(@Argument Long id) {
        return entityService.findById(id).get();
    }
}
