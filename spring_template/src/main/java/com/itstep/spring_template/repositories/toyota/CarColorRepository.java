package com.itstep.spring_template.repositories.toyota;

import com.itstep.spring_template.models.toyota.CarColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarColorRepository extends JpaRepository<CarColor, Long>
{
}
