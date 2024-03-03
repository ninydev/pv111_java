package com.itstep.spring_template.controllers.toyota;

import com.itstep.spring_template.models.toyota.CarModel;
import com.itstep.spring_template.repositories.toyota.CarModelRepository;
import com.itstep.spring_template.services.toyota.CarModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/toyota/models")
public class CarModelController
{
    private final CarModelService carModelService;

    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CarModel> create(@RequestBody CarModel model) {

        CarModel createdModel = carModelService.save(model);

        // Ссылка (URI) на запрос к этой сущности
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdModel.getId()).toUri();

        // Вернуть мне нужно статус - что создалось и что создалось
        return ResponseEntity.created(location).body(createdModel);
    }

    @GetMapping("")
    public ResponseEntity<Page<CarModel>> readAll(Pageable pageable) {
        Page<CarModel> result = carModelService.findAll(pageable);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarModel> readById(@PathVariable Long id) {

        Optional<CarModel> result = carModelService.findById(id);

        return result.map(
                        ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
