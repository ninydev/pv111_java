package com.itstep.first_spring.services.media;

import com.itstep.first_spring.services.storages.StorageAvatarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
@AllArgsConstructor
public class ConvertAvatarMediaService {

    private final StorageAvatarService storageAvatarService;

    public void convertAvatar(long userId) throws IOException {
        byte[] originalBytes = storageAvatarService.getOriginalBytes(userId);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(originalBytes));

        // Проверяем, было ли успешно считано изображение
        if (image == null) {
            throw new IOException("Failed to read image");
        }

        // Создаем буфер для хранения конвертированного изображения
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Конвертируем изображение в формат WebP
        boolean success = ImageIO.write(image, "webp", outputStream);

        // Проверяем, была ли успешной конвертация
        if (!success) {
            throw new IOException("Failed to convert image to WebP format");
        }

        // Получаем байтовое представление изображения в формате WebP
        byte[] webpData = outputStream.toByteArray();

        // Закрываем поток вывода
        outputStream.close();

        storageAvatarService.putWebP(userId, webpData);
    }

}
