package com.itstep.first_spring.controllers.auth;

import com.itstep.first_spring.dto.auth.UserDTO;
import com.itstep.first_spring.dto.errors.ErrorResponseDTO;
import com.itstep.first_spring.exceptions.StatusException;
import com.itstep.first_spring.exceptions.auth.EmailInvalidException;
import com.itstep.first_spring.exceptions.auth.UsernameInvalidException;
import com.itstep.first_spring.models.auth.UserModel;
import com.itstep.first_spring.requests.auth.SignInRequest;
import com.itstep.first_spring.requests.auth.SignUpRequest;
import com.itstep.first_spring.response.auth.JwtAuthenticationResponse;
import com.itstep.first_spring.services.auth.AuthenticationService;
import com.itstep.first_spring.services.auth.UserService;
import com.itstep.first_spring.services.storages.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final StorageService storageService;

    @Operation(summary = "Авторизация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная авторизация",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtAuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка при авторизации",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request) {
        JwtAuthenticationResponse response;
        try {
            response = authenticationService.signIn(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            System.out.println(ex.getClass());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDTO(ex));
        }
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) throws UsernameInvalidException, EmailInvalidException, StatusException {
        storageService.putToTemp("signUp", request.getUsername());
        return authenticationService.signUp(request);
    }


    @Operation(summary = "Получение текущего пользователя (по ключу)")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(){
        return ResponseEntity.ok(new UserDTO(userService.getCurrentUser()));
    }
}
