package com.itstep.first_spring.services.auth;


import com.itstep.first_spring.dto.auth.UserDTO;
import com.itstep.first_spring.dto.websocket.test.GreetingMessageDTO;
import com.itstep.first_spring.exceptions.StatusException;
import com.itstep.first_spring.exceptions.auth.EmailInvalidException;
import com.itstep.first_spring.exceptions.auth.UsernameInvalidException;
import com.itstep.first_spring.models.auth.RoleEnum;
import com.itstep.first_spring.models.auth.UserModel;
import com.itstep.first_spring.requests.auth.SignInRequest;
import com.itstep.first_spring.requests.auth.SignUpRequest;
import com.itstep.first_spring.response.auth.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
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
    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws UsernameInvalidException, EmailInvalidException, StatusException {

        var user = UserModel.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, new UserDTO(user));
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

//        var userDetails = userService
//                .userDetailsService()
//                .loadUserByUsername(request.getUsername());

        UserModel user = userService.getByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);

        simpMessagingTemplate.convertAndSend("/topic/greetings",
                new GreetingMessageDTO("Приветсвуем: " + user.getUsername()));

        return new JwtAuthenticationResponse(jwt, new UserDTO(user));
    }
}