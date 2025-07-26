package com.url.shortner.controller;

import com.url.shortner.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final long MAX_FILE_SIZE = 5*1024*1024;
    private static final List<String> ALLOWED_MIME_TYPES = List.of("application/pdf", "application/txt");
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity uploadToLocalFilesStorage(@RequestParam("file")MultipartFile file, @RequestParam("type") String type){

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File cannot be empty");
        }

        // 2. Validate file size
        if (file.getSize() > MAX_FILE_SIZE) {
            return ResponseEntity.badRequest()
                    .body("File size exceeds maximum limit of 5MB");
        }

        // 3. Validate content type
        if (!ALLOWED_MIME_TYPES.contains(file.getContentType())) {
            return ResponseEntity.badRequest()
                    .body("Invalid file type. Allowed types: " + ALLOWED_MIME_TYPES);
        }
        //log.info("{} : File Received, Uploading Started", FileController.class);
        String url = uploadService.uploadFile(file, type);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        //log.info("{} : File Download Initiated", FileController.class);
        return null;
    }

}
