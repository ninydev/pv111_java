package org.itstep.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable
{

    private String name;
    private String email;
    private String password;

    // Пользователь имеет большое количество ролей
    private ArrayList<Role> roles = new ArrayList<>();

    @Override
    public int compareTo(Object o) {
        return -this.name.compareTo(((User)o).getName());
    }
}
