package com.itstep.first_spring.services.auth;

import com.itstep.first_spring.exceptions.StatusException;
import com.itstep.first_spring.exceptions.auth.EmailInvalidException;
import com.itstep.first_spring.exceptions.auth.UsernameInvalidException;
import com.itstep.first_spring.models.auth.RoleEnum;
import com.itstep.first_spring.models.auth.UserModel;
import com.itstep.first_spring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public UserModel save(UserModel user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public UserModel create(UserModel user) throws UsernameInvalidException, EmailInvalidException, StatusException {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            // throw new UsernameInvalidException("Пользователь с таким именем уже существует");
            throw new StatusException("Bad User Name", HttpStatus.FORBIDDEN);
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new EmailInvalidException("Пользователь с таким email уже существует");
        }

        return this.save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public UserModel getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserModel getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    /**
     * Выдача прав администратора текущему пользователю
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(RoleEnum.ROLE_ADMIN);
        save(user);
    }
}