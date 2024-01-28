package com.mwambacodes.identityservice.modules.Core.Media.Action.MediaUpload;

import com.mwambacodes.identityservice.config.FileStorageConfig;
import com.mwambacodes.identityservice.modules.Core.Media.MediaEntity;
import com.mwambacodes.identityservice.modules.Core.Media.MediaRepository;
import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaUploadService {

    private final MediaRepository mediaRepository;
    private final FileStorageConfig fileStorageConfig;
    private final Logger logger = LoggerFactory.getLogger(MediaUploadService.class);

    public MediaEntity init(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store an empty file");
        }

        Path fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir());
        try {
            Files.createDirectories(fileStorageLocation.toAbsolutePath().normalize());
        } catch (Exception e) {
            throw new RuntimeException("Could not create directory where files should be saved.");
        }

        String[] splits = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String extension = splits[splits.length-1];
        String date = DateTimeFormatter
                .ofPattern("yyyyMMddHHmmssSSS")
                .format(LocalDateTime.now());

        String name = date + "." + extension;

        Path destinationFile = fileStorageLocation.resolve(Paths.get(name)).normalize().toAbsolutePath();

        if (!destinationFile.getParent().equals(fileStorageLocation.toAbsolutePath())) {
            throw new RuntimeException("Cannot store file outside of upload directory");
        }

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: ", e);
        }


        MediaEntity mediaEntity = mediaRepository.save(MediaEntity.builder()
                .uploadedBy((UserEntity) Helpers.loggedInUser)
                .extension(extension)
                .originalName(file.getOriginalFilename())
                .size((double) file.getSize())
                .uploadedDate(LocalDateTime.now())
                .name(name)
                .uuid(UUID.randomUUID())
                .build()
        );

        if (mediaEntity.getId() > 0) return mediaEntity;


        throw new RuntimeException("Failed to upload File: An error occurred.");
    }

}
