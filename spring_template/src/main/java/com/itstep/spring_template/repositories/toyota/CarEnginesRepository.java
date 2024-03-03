package com.itstep.spring_template.repositories.toyota;

import com.itstep.spring_template.models.toyota.CarEngine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEnginesRepository extends JpaRepository<CarEngine, Long>
{
}
