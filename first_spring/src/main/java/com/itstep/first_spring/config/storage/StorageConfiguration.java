package com.itstep.first_spring.config.storage;

import com.itstep.first_spring.services.storages.StorageService;
import com.itstep.first_spring.storages.drivers.StorageDriverInterface;
import com.itstep.first_spring.storages.drivers.StorageMinIoDriver;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {


    @Value("${storage.minio.endpoint}")
    private String endpoint;// = "http://localhost:9000"; // Замените на ваше значение

    @Value("${storage.minio.access.key}")
    private String accessKey; // = "o8x5Uwv0PPB1YeeM0U5w"; // Замените на ваше значение

    @Value("${storage.minio.secret.key}")
    private String secretKey; // = "wOxeP8y1G04Hx3SpSivj27tQftmc2M8b7PGTE2xp"; // Замените на ваше значение


    /**
     *  Суть этой иньекции - построить и отдать пользователю интерфейс для работы с дисковой системой
     *  В том числе - вариант - когда таких систем несколько
     *
     *  Тут выполняем подключение к клиенту и оборачиваем подключение в наш интерфейс
     *  В случае - если работа будет идти с несколькими хранилищами (S3, MinIo, Azure) одновременно
     *  нужно будет добавить еще один фасад - который будет уметь еще и переключать между собой хранилища,
     *  а так же содержать коллекцию хранилищ
     *
     * @return StorageDriverInterface - экземпляр нужного клиента хранилища в нашей оболочке !!!!
     */
    @Bean
    public StorageDriverInterface StorageService() {


        // Создали клиента - и подключили его к хранилищу
        MinioClient client =
                MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();

        // Обернули клиента нашей оболочкой - привели имена к норме
        StorageDriverInterface clientInterface = new StorageMinIoDriver(client);

        return  clientInterface;

        // Когда мне понадобиться другое хранилище - я создам тут клиента - оберну его своим интерфейсом - и отдам наружу
//        S3Client s3 = S3Client.builder()
//                .region(region)
//                .build();
//
//        StorageDriverInterface clientInterface = new StorageS3Driver(client);
//        return  clientInterface;

    }
}
