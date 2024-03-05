package com.itstep.first_spring.controllers.auth;

import com.itstep.first_spring.dto.auth.UserDTO;
import com.itstep.first_spring.models.auth.UserModel;
import com.itstep.first_spring.requests.auth.SignInRequest;
import com.itstep.first_spring.requests.auth.SignUpRequest;
import com.itstep.first_spring.response.auth.JwtAuthenticationResponse;
import com.itstep.first_spring.services.auth.AuthenticationService;
import com.itstep.first_spring.services.auth.UserService;
import com.itstep.first_spring.services.storages.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final StorageService storageService;


    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        storageService.putToTemp("signUp", request.getUsername());
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        storageService.putToTemp("signIn", request.getUsername());
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Получение текущего пользователя (по ключу)")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(){
        return ResponseEntity.ok(new UserDTO(userService.getCurrentUser()));
    }
}
