package org.sound_cloud.api.services.auth;

import lombok.RequiredArgsConstructor;
import org.sound_cloud.api.exceptions.StatusException;
import org.sound_cloud.api.exceptions.auth.EmailInvalidException;
import org.sound_cloud.api.models.auth.RoleEnum;
import org.sound_cloud.api.models.auth.UserModel;
import org.sound_cloud.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


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
    public UserModel create(UserModel user) throws StatusException, EmailInvalidException {
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