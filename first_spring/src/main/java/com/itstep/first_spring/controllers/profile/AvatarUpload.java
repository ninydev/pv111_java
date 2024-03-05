package com.itstep.first_spring.controllers.profile;

import com.itstep.first_spring.services.auth.UserService;
import com.itstep.first_spring.services.media.ConvertAvatarMediaService;
import com.itstep.first_spring.services.storages.StorageAvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/profile/avatar")
@RequiredArgsConstructor
public class AvatarUpload
{

    private final StorageAvatarService avatarService;
    private final UserService userService;
    private final ConvertAvatarMediaService convertAvatarMediaService;

    @PostMapping("/upload")

    public ResponseEntity<String> uploadAvatar(@RequestParam("avatar") MultipartFile avatar) {
        if (avatar.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
        }

        try {
            avatarService.putOriginal(userService.getCurrentUser().getId(), avatar.getBytes());
            convertAvatarMediaService.convertAvatar(userService.getCurrentUser().getId());



//            // Получаем имя файла
//            String fileName = file.getOriginalFilename();
//            // Определяем путь для сохранения файла
//            String filePath = UPLOAD_DIR + fileName;
//            // Создаем объект File с указанным путем
//            File dest = new File(filePath);
//            // Сохраняем файл на сервере
//            file.transferTo(dest);
//            // Возвращаем сообщение об успешной загрузке
            return new ResponseEntity<>("File uploaded successfully: ", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
