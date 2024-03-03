package com.itstep.spring_template.services.auth;

import com.itstep.spring_template.dto.auth.UserDTO;
import com.itstep.spring_template.models.auth.Role;
import com.itstep.spring_template.models.auth.User;
import com.itstep.spring_template.request.auth.SignInRequest;
import com.itstep.spring_template.request.auth.SignUpRequest;
import com.itstep.spring_template.response.auth.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
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

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        user.setAuthor(user);

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

        User user = userService.getByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, new UserDTO(user));
    }
}
