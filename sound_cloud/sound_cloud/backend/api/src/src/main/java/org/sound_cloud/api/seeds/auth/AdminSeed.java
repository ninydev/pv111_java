package org.sound_cloud.api.seeds.auth;

import org.sound_cloud.api.models.auth.RoleEnum;
import org.sound_cloud.api.models.auth.UserModel;
import org.sound_cloud.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminSeed implements CommandLineRunner {

    private final UserRepository adminRepository;


    @Autowired
    public AdminSeed(UserRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Проверяем, существует ли администратор с указанным email
        if (!adminRepository.existsByEmail("admin@admin.com")) {
            // Если не существует, создаем нового администратора
            UserModel admin = new UserModel();
            admin.setUsername("admin");
            admin.setRole(RoleEnum.ROLE_ADMIN);
            admin.setEmail("admin@admin.com");
            admin.setPassword("QweAsdZxc!23");

            // Сохраняем администратора в базе данных
            adminRepository.save(admin);
            System.out.println("Администратор admin@admin.com QweAsdZxc!23 создан");
        } else {
            // Если администратор с указанным email уже существует, вы можете выполнить здесь необходимые действия для обновления записи, если это требуется.
            System.out.println("Администратор с указанным email уже существует.");
        }
    }
}