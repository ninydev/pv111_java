package com.itstep.first_spring.storages.drivers;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class StorageMinIoDriver implements StorageDriverInterface
{
    @Value("${storage.minio.endpoint}")
    private String endpoint = "http://localhost:9000"; // Замените на ваше значение

    @Value("${storage.minio.access.key}")
    private String accessKey = "o8x5Uwv0PPB1YeeM0U5w"; // Замените на ваше значение

    @Value("${storage.minio.secret.key}")
    private String secretKey = "wOxeP8y1G04Hx3SpSivj27tQftmc2M8b7PGTE2xp"; // Замените на ваше значение

    private MinioClient minioClient;


    private void checkConnect() {
        if (minioClient == null) {
            this.minioClient =
                    MinioClient.builder()
                            .endpoint(endpoint)
                            .credentials(accessKey, secretKey)
                            .build();
        }
    }

    /**
     * Проверяем - есть ли контейнер (корзина, дирректория) для хранения
     * @param bucketName
     */
    private void checkBucket(String bucketName)
            throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        checkConnect();
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
        checkConnect();
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
