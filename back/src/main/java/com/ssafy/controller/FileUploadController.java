package com.ssafy.controller;

import com.ssafy.model.UploadFile;
import com.ssafy.service.FileUploadDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

    private final FileUploadDownloadService service;

    @Autowired
    public FileUploadController(FileUploadDownloadService service) {
        this.service = service;
    }

    @PostMapping("/uploadFile")
    public UploadFile uploadFile(@RequestParam("file") MultipartFile file) throws NoSuchAlgorithmException {
        UploadFile uploadFile = service.storeFile(file);

        return uploadFile;
    }

}
