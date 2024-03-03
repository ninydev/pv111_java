package com.itstep.spring_template.response.auth;

import com.itstep.spring_template.dto.auth.UserDTO;
import com.itstep.spring_template.models.auth.Role;
import com.itstep.spring_template.models.auth.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ c токеном доступа")
public class JwtAuthenticationResponse {
    @Schema(description = "Токен доступа", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String token;

    private UserDTO user;

//    private long user_id;
//
//    private Role user_role;
}
