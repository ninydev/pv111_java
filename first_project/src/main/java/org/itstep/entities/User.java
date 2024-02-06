package org.itstep.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String email;
    private String password;



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
