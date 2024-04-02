package org.sound_cloud.api.services.auth;



import lombok.RequiredArgsConstructor;
import org.sound_cloud.api.dto.auth.UserDTO;
import org.sound_cloud.api.exceptions.StatusException;
import org.sound_cloud.api.exceptions.auth.EmailInvalidException;
import org.sound_cloud.api.models.auth.RoleEnum;
import org.sound_cloud.api.models.auth.UserModel;
import org.sound_cloud.api.requests.auth.SignInRequest;
import org.sound_cloud.api.requests.auth.SignUpRequest;
import org.sound_cloud.api.response.auth.JwtAuthenticationResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RabbitTemplate rabbitTemplate;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws Exception {

        var user = UserModel.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.ROLE_USER)
                .build();

        userService.create(user);
        UserDTO userDTO = new UserDTO(user);
        // System.out.println(userDTO.toJson());
        rabbitTemplate.convertAndSend("user.register", userDTO.toJson());
        //rabbitTemplate.convertSendAndReceive("user.register", userDTO.toJson());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, userDTO);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserModel user = userService.getByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt, new UserDTO(user));
    }
}