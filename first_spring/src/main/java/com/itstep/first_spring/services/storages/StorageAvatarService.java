package com.itstep.first_spring.services.storages;

import com.itstep.first_spring.models.auth.UserModel;
import com.itstep.first_spring.storages.drivers.StorageServiceDriver;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class StorageAvatarService {

//    private final StorageServiceDriver storage;
//    private final String avatarBucketName = "avatars";
//
//    public StorageAvatarService(StorageServiceDriver storage) {
//        this.storage = storage;
//    }
//
//    public void putOriginal(int user_id, File file) {
//        String path = "/" + user_id + "/original";
//        try {
//            storage.put(avatarBucketName, path, file);
//        } catch (Exception e) {
//            System.out.println(" Error ");
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//
//    }
}
