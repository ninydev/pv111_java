package com.itstep.spring_template.models.toyota;

import com.itstep.spring_template.models.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
        import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "car_equipments")
@Schema(description = " Доступные комплектации ")
public class CarEquipment extends BaseEntity
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel model;

    @ManyToMany
    @JoinTable(name = "equipment_colors",
            joinColumns = { @JoinColumn(name = "equipment_id") },
            inverseJoinColumns = { @JoinColumn(name = "color_id") }
    )
    private Set<CarColor> colors;

    @ManyToMany
    @JoinTable(name = "equipment_engines",
            joinColumns = { @JoinColumn(name = "equipment_id") },
            inverseJoinColumns = { @JoinColumn(name = "engine_id") }
    )
    private Set<CarEngine> engines;


}
