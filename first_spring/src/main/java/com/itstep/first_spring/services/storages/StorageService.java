package com.itstep.first_spring.services.storages;

import com.itstep.first_spring.storages.drivers.StorageDriverInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StorageService {

    private static String tempBucketName = "temp";

    private final StorageDriverInterface storage;

    public void putToTemp(String fileName, String data) {
        try {
            storage.put(tempBucketName, fileName, data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
