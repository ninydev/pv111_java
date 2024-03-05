package com.itstep.first_spring.storages.drivers;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class StorageMinIoDriver implements StorageDriverInterface
{
    @Value("${storage.minio.endpoint}")
    private String endpoint; // Замените на ваше значение

    @Value("${storage.minio.access.key}")
    private String accessKey; // Замените на ваше значение

    @Value("${storage.minio.secret.key}")
    private String secretKey; // Замените на ваше значение

    private final MinioClient minioClient;
    public StorageMinIoDriver() {
        this.minioClient =
                MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();
    }

    /**
     * Проверяем - есть ли контейнер (корзина, дирректория) для хранения
     * @param bucketName
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    private void checkBucket(String bucketName)
            throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
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
}
