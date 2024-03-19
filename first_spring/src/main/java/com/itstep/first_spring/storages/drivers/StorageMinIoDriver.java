package com.itstep.first_spring.storages.drivers;

import io.minio.*;
import io.minio.errors.*;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * Этот класс должен обренуть операции с файлами с клиентом MinIO
 */

public class StorageMinIoDriver implements StorageDriverInterface
{

    private final MinioClient minioClient;

    public StorageMinIoDriver(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    /**
     * Проверяем - есть ли контейнер (корзина, дирректория) для хранения
     * @param bucketName
     */
    private void checkBucket(String bucketName)
            throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // checkConnect();
        // Make  bucket if not exist.
        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            // Make a new bucket
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            System.out.println("Bucket 'asiatrip' already exists.");
        }
    }


    @Override
    public byte[] getBytes(String bucketName, String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // checkConnect();
        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .build());
        return stream.readAllBytes();
    }

    @Override
    public void put(String bucketName, String path, File file)
            throws IOException, ServerException, InsufficientDataException,
            ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        checkBucket(bucketName);
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(path)
                        .filename(file.getAbsolutePath())
                        .build());
    }

    @Override
    public void put(String bucketName, String path, String data)
            throws IOException, ServerException, InsufficientDataException,
            ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        checkBucket(bucketName);
        ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes("UTF-8"));

        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(path).stream(
                                bais, bais.available(), -1)
                        .build());
        bais.close();
    }


    public void put(String bucketName, String path, byte[] bytes)
            throws IOException, ServerException, InsufficientDataException,
            ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        checkBucket(bucketName);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(path).stream(
                                bais, bais.available(), -1)
                        .build());
        bais.close();
    }
}
