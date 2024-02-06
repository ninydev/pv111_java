package org.itstep.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String email;
    private String password;

    // Пользователь имеет большое количество ролей
    private ArrayList<Role> roles = new ArrayList<>();



//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    //    public void SET_NAME(String name) {
//        this.name = name;
//    }
//
//    public String getUserName() {
//        return this.name;
//    }




}
