package org.sound_cloud.out_requests.services;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class StorageService {

    @Value("${storage.minio.endpoint}")
    private String endpoint;// = "http://localhost:9000"; // Замените на ваше значение

    @Value("${storage.minio.access.key}")
    private String accessKey; // = "o8x5Uwv0PPB1YeeM0U5w"; // Замените на ваше значение

    @Value("${storage.minio.secret.key}")
    private String secretKey; // = "wOxeP8y1G04Hx3SpSivj27tQftmc2M8b7PGTE2xp"; // Замените на ваше значение
    MinioClient minioClient;

    public StorageService(){
        // Создали клиента - и подключили его к хранилищу
        MinioClient client =
                MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();
    }

    public void downloadAvatar(Long userId, String imageUrl) throws Exception {
        // Загрузка данных изображения по URL
        URL url = new URL(imageUrl);
        InputStream in = url.openStream();
        // Создание временного файла для хранения данных изображения
        Path tempFile = Files.createTempFile("temp", ".image");
        Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);

        // Загрузка временного файла в хранилище MinIO
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket("avatars")
                        .object(userId.toString() + "/avatar.image")
                        .filename(tempFile.getFileName().toString())
                        .contentType("image/webp")
                        .build());
    }



}
