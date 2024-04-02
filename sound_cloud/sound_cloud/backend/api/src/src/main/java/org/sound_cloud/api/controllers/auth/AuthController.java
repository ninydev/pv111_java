package org.sound_cloud.api.controllers.auth;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sound_cloud.api.dto.auth.UserDTO;
import org.sound_cloud.api.dto.errors.ErrorResponseDTO;
import org.sound_cloud.api.exceptions.StatusException;
import org.sound_cloud.api.exceptions.auth.EmailInvalidException;
import org.sound_cloud.api.requests.auth.SignInRequest;
import org.sound_cloud.api.requests.auth.SignUpRequest;
import org.sound_cloud.api.response.auth.JwtAuthenticationResponse;
import org.sound_cloud.api.services.auth.AuthenticationService;
import org.sound_cloud.api.services.auth.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

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
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) throws Exception {
        return authenticationService.signUp(request);
    }


    @Operation(summary = "Получение текущего пользователя (по ключу)")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(){
        return ResponseEntity.ok(new UserDTO(userService.getCurrentUser()));
    }
}
