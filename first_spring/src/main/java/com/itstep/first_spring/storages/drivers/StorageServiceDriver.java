package com.itstep.first_spring.storages.drivers;

import io.minio.errors.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class StorageServiceDriver implements StorageDriverInterface{
    private final StorageDriverInterface driver;

    public StorageServiceDriver() {
        // В этой точке я могу менять - какой драйвер у меня будет
        driver = new StorageMinIoDriver();
    }

    @Override
    public void put(String bucketName, String path, File file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        driver.put(bucketName, path, file);
    }

    @Override
    public void put(String bucketName, String path, String data) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        driver.put(bucketName, path, data);
    }

    public void put(String bucketName, String path, byte[] bytes) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        driver.put(bucketName, path, bytes);
    }
}
