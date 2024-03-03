package com.itstep.spring_template.dto.auth;

import com.itstep.spring_template.models.auth.Role;
import com.itstep.spring_template.models.auth.User;
import lombok.Data;

@Data
public class UserDTO
{
    private Long id;
    private Role role;

    public UserDTO(User user) {
        id = user.getId();
        role = user.getRole();
    }
}
