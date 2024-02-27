package com.itstep.first_spring.controllers;

import com.itstep.first_spring.models.EntityModel;
import com.itstep.first_spring.repositories.EntityRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entity")
public class EntityController {

    private final EntityRepository entityRepository;
    public EntityController(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    /**
     * Create
     * @param newEntity
     * @return
     */
    @PostMapping("")
    public ResponseEntity<EntityModel> create(@RequestBody EntityModel newEntity) {

        // Ссылка (указатель) на вновь созданный экземпляр сущности
        EntityModel createdEntity = entityRepository.save(newEntity);

        // Ссылка (URI) на запрос к этой сущности
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdEntity.getId()).toUri();

        // Вернуть мне нужно статус - что создалось и что создалось
        return ResponseEntity.created(location).body(createdEntity);
    }


    /**
     * Read All
     * !!! Add Paginate
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<EntityModel>> readAll() {
        List<EntityModel> result = new ArrayList<>();
        entityRepository.findAll().forEach(result::add);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    /**
     * Read By Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel> readById(@PathVariable Long id) {

        Optional<EntityModel> result = entityRepository.findById(id);

        return result.map(
                        ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
