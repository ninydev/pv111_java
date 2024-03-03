package com.itstep.spring_template.repositories.toyota;

import com.itstep.spring_template.models.toyota.CarEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEquipmentRepository extends JpaRepository<CarEquipment, Long>
{
}
