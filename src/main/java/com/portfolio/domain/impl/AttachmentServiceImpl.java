package com.portfolio.domain.impl;

import com.portfolio.domain.common.AttachmentUploadCommand;
import com.portfolio.domain.model.attachment.Attachment;
import com.portfolio.domain.model.attachment.AttachmentData;
import com.portfolio.domain.model.attachment.AttachmentRepository;
import com.portfolio.domain.model.attachment.AttachmentService;
import com.portfolio.infrastructure.file.local.FileStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    @Value("${app.server.home}")
    private String server;

    private final FileStorage fileStorage;
    private final AttachmentRepository attachmentRepository;

    @Override
    public AttachmentData uploadFile(AttachmentUploadCommand command) throws IOException {

        MultipartFile file = command.getFile();

        // 이미지만 받습니다
        String contentType = file.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException();
        }
        if (contentType.startsWith("image/svg")){
            throw new IllegalArgumentException();
        }

        String uploadFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        String yyMMdd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

        String unique_fileName = uuid + "-" + uploadFileName;

        String osName = System.getProperty("os.name");
        String filePath;
        String publicUrl = server + "/file/" + uuid + "-" + uploadFileName;

        if (osName.startsWith("Mac")) {
            // Code for Mac
            String directoryPath = "/Users/jaesuk/file/" + yyMMdd;
            // Create a Path object from the directory path
            Path targetLocation = Paths.get(directoryPath);
            filePath = createDirectory(directoryPath, unique_fileName, targetLocation);

            try {
//                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                Path dest_filePath = Paths.get(directoryPath, unique_fileName);
                File dest = new File(dest_filePath.toString());
                file.transferTo(dest);

            } catch (IOException e) {
                throw new IllegalArgumentException("Multipart file '" + targetLocation.toString() + "' 에 저장 실패 했습니다.", e);
            }

        } else if (osName.startsWith("Linux")) {
            // Code for Ubuntu
            String directoryPath = "/home/central/file/" + yyMMdd + "/";
            // Create a Path object from the directory path
            Path targetLocation = Paths.get(directoryPath);
            filePath = createDirectory(directoryPath, unique_fileName, targetLocation);

            try {
                byte[] data = file.getBytes();
                saveFile(data, directoryPath, unique_fileName);
                log.info("Filed uploaded successfully");
            } catch (IOException e) {
                log.error(e.getMessage());
            }

//            try {
////                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//                Path dest_filePath = Paths.get(directoryPath, unique_fileName);
//                File dest = new File(dest_filePath.toString());
//                file.transferTo(dest);
//
//            } catch (IOException e) {
//                throw new IllegalArgumentException("Multipart file '" + targetLocation.toString() + "' 에 저장 실패 했습니다.", e);
//            }
        } else {
            // Unsupported operating system
            throw new RuntimeException("Unsupported operating system: " + osName);
        }

        Path imagePath = Paths.get(filePath);
        String fileType = Files.probeContentType(imagePath);

        Attachment attachment = Attachment.builder()
                .fileName(file.getOriginalFilename())
                .filePath(filePath)
                .publicUrl(publicUrl)
                .thumbnailCreated(false)
                .fileType(fileType)
                .build();

        attachmentRepository.save(attachment);

        return AttachmentData.builder()
                .id(attachment.getId())
                .filePath(filePath)
                .publicUrl(publicUrl)
                .fileType(fileType)
                .build();
    }

    public void saveFile(byte[] data, String directoryPath, String uniqueFileName) throws IOException {

        log.info("Data length: " + data.length);
        log.info("Data contents: " + new String(data));

        Path filePath = Paths.get(directoryPath, uniqueFileName);
        Files.write(filePath, data);
    }

    private String createDirectory(String directoryPath, String unique_fileName, Path targetLocation) {
        String filePath = directoryPath + unique_fileName;
        // Check if the directory already exists
        if (!Files.exists(targetLocation)) {
            // If the directory doesn't exist, create it and any necessary parent directories
            try {
                Files.createDirectories(targetLocation);
                System.out.println("Directory created successfully.");
            } catch (IOException e) {
                // Handle the exception
                System.err.println("Failed to create directory: " + e.getMessage());
            }
        } else {
            System.out.println("Directory already exists.");
        }
        return filePath;
    }

    //             fileStorage.saveFile();
    //
    //            String fileName = uuid + "-" + uploadFileName;
    //
    //            String path = "/Users/jaesuk/Pictures/" + yyMMdd + "/" + fileName;
    //            String public_url = server + "/" + fileName;
    //
    //            File originalImageFile = new File(path);
    //
    //            // generate thumbnail and save
    //            File thumbnail = new File("/" + uploadFileName);
    //            ImageUtils.saveImageWithThumbnail(originalImageFile,thumbnail);
}
