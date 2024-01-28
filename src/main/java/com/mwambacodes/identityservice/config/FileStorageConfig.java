package com.mwambacodes.identityservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {

    @Value("#{'${file.upload-dir}'}")
    private String  uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
}
