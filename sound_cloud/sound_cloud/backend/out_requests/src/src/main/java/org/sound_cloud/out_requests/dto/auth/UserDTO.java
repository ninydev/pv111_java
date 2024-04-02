package org.sound_cloud.out_requests.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sound_cloud.out_requests.models.auth.RoleEnum;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable
{
    private Long id;
    private String username;
    private String email;
    private RoleEnum role;

}
