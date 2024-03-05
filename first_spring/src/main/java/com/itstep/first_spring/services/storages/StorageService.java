package com.itstep.first_spring.services.storages;

import com.itstep.first_spring.storages.drivers.StorageDriverInterface;
import com.itstep.first_spring.storages.drivers.StorageMinIoDriver;
import com.itstep.first_spring.storages.drivers.StorageServiceDriver;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class StorageService {

    private final StorageServiceDriver storage =  new StorageServiceDriver();
    private final String tempBucketName = "temp";


    public void putToTemp(String fileName, String data) {
        try {
            storage.put(tempBucketName, fileName, data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
