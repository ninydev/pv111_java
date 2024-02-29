package com.itstep.first_spring.repositories;

import com.itstep.first_spring.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long>
{
}
