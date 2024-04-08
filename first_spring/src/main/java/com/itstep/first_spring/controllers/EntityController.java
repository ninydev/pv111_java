package com.itstep.first_spring.controllers;

import com.itstep.first_spring.models.EntityModel;
import com.itstep.first_spring.repositories.EntityRepository;
import com.itstep.first_spring.requests.EntityModelCreateRequest;
import com.itstep.first_spring.services.EntityService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entity")
@Validated
@AllArgsConstructor
public class EntityController {

    private final EntityService entityService;

    /**
     * Create
     * @param request
     * @return
     */
    @PostMapping("")
    public ResponseEntity<EntityModel> save(
            @RequestBody EntityModelCreateRequest request
    ) {

        // Ссылка (указатель) на вновь созданный экземпляр сущности
        EntityModel createdEntity = entityService.save(request.toEntity());

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
    public ResponseEntity<Page<EntityModel>> findAll(Pageable pageable) {
        Page<EntityModel> result = entityService.findAll(pageable);

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
    public ResponseEntity<EntityModel> findById(@PathVariable Long id) {

        Optional<EntityModel> result = entityService.findById(id);

        return result.map(
                        ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
