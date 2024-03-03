package com.itstep.spring_template.models.toyota;

import com.itstep.spring_template.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "car_colors")
public class CarColor extends BaseEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany (mappedBy = "colors")
    private Set<CarEquipment> equipments;
}