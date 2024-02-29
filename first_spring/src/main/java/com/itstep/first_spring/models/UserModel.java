package com.itstep.first_spring.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
@Schema(description = " User Model ")
public class UserModel extends BaseEntity
{

    @Column(unique = true) // Уникальность почты
    @Email
    @Schema(description = "User email", example = "none@none.com")
    private String email;

    @Schema(description = "User password (mast have [A-Z],[a-z], [0-9], >6 )", example = "QweAsdZxc!23")
    @Size(min = 6, max = 100)
    private String password;


    @PrePersist
    @PreUpdate
    private void hashPassword() {
        // Проверка на длину пароля и наличие символов
        if (password == null || password.length() < 6 ||
                !password.matches(".*[A-Z].*") || // Проверка наличия большой буквы
                !password.matches(".*[a-z].*") || // Проверка наличия маленькой буквы
                !password.matches(".*\\d.*")) {  // Проверка наличия цифры
            throw new IllegalArgumentException("Пароль должен быть не менее 6 символов и содержать хотя бы одну большую букву, одну маленькую букву и одну цифру");
        }

        // Потом добавлю иньекцию
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(this.password);
    }

}
