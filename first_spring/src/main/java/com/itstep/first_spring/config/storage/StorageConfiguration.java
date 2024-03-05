package com.itstep.first_spring.config.storage;

import com.itstep.first_spring.storages.drivers.StorageMinIoDriver;
import com.itstep.first_spring.storages.drivers.StorageServiceDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {

    @Bean
    public StorageMinIoDriver storageMinIoDriver() {
        return new StorageMinIoDriver();
    }

    @Bean
    public StorageServiceDriver storageServiceDriver() {
        return  new StorageServiceDriver();
    }
}
