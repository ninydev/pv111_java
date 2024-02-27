package com.itstep.first_spring.repositories;

import com.itstep.first_spring.models.EntityModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Реализация данного интерфейса создаст мне все методы для
 * построения SQL запросов к сущности
 * И создан он будет автоматически
 */
public interface EntityRepository extends JpaRepository<EntityModel, Long>
{
}
