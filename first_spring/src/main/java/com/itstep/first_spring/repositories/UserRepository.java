package com.itstep.first_spring.repositories;

import com.itstep.first_spring.models.auth.UserModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, Long>
{

    Optional<UserModel> findByEmail(String email);

    @Cacheable(cacheNames = "users", key = "#username")
    Optional<UserModel> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
