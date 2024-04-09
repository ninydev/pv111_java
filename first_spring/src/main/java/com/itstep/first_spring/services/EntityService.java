package com.itstep.first_spring.services;

import com.itstep.first_spring.models.EntityModel;
import com.itstep.first_spring.repositories.EntityRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class EntityService {

    private static final Logger log = LoggerFactory.getLogger(EntityService.class);
    private final EntityRepository entityRepository;

    @CacheEvict(cacheNames = "entities", allEntries = true)
    public EntityModel save(EntityModel entityModel) {
        // clearCache();
        return entityRepository.save(entityModel);
    }

    @Cacheable(cacheNames = "entities", key = "#pageable")
    public Page<EntityModel> findAll( Pageable pageable) {
        log.info("find All");
        return entityRepository.findAll(pageable);
    }

    public List<EntityModel> findAll( ) {
        return entityRepository.findAll();
    }

    @Cacheable(cacheNames = "entities", key = "#id")
    public Optional<EntityModel> findById(Long id) {
        log.info("find by id: {}", id);
        return entityRepository.findById(id);
    }
}
