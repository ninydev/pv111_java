package com.itstep.first_spring.models.auth;

import com.itstep.first_spring.models.BaseEntity;
import com.itstep.first_spring.models.PerformerModel;
import com.itstep.first_spring.models.SongModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Schema(description = " User Model ")
public class UserModel extends BaseEntity implements UserDetails
{
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(unique = true) // Уникальность почты
    @Email
    @Schema(description = "User email", example = "none@none.com")
    private String email;

    @Schema(description = "User password (mast have [A-Z],[a-z], [0-9], >6 )", example = "QweAsdZxc!23")
    @Size(min = 6, max = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEnum role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "performer_id", referencedColumnName = "id")
    @Nullable
    @Schema(description = "If user is performer")
    private PerformerModel performer;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "uploader")
    @Schema(description = "User Songs")
    protected Set<SongModel> songs;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    @PreUpdate
    private void beforeCreateOrUpdate(){
        hashPassword();
    }

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
