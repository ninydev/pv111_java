package com.itstep.spring_template.repositories.toyota;

import com.itstep.spring_template.models.toyota.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long>
{
}
