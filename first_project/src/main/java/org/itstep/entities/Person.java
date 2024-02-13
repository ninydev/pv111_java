package org.itstep.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.itstep.entities.types.AvailableColor;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private Boolean sex;
    private int age;
    private AvailableColor eye;
    private AvailableColor hair;
}
