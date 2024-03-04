package com.itstep.first_spring.dto.auth;


import com.itstep.first_spring.models.auth.RoleEnum;
import com.itstep.first_spring.models.auth.UserModel;
import lombok.Data;

@Data
public class UserDTO
{
    private final Long id;
    private final String username;
    private final String email;
    private final RoleEnum role;

    public UserDTO(UserModel user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        role = user.getRole();
    }
}
