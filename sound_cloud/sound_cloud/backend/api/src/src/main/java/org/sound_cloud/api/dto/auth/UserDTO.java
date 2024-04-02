package org.sound_cloud.api.dto.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.sound_cloud.api.models.auth.RoleEnum;
import org.sound_cloud.api.models.auth.UserModel;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable
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

    public String toJson() throws Exception {
        // Создание ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Преобразование объекта UserDTO в JSON строку
        return objectMapper.writeValueAsString(this);
    }
}
