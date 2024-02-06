package org.itstep.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Role {
    private String name;

    public Role(String name) {
        this.name = name;
    }

    @ToString.Exclude
    private ArrayList<User> users = new ArrayList<>();
}
