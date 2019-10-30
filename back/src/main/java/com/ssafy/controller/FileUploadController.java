package com.ssafy.controller;

import com.ssafy.model.UploadFile;
import com.ssafy.service.FileUploadDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {

    private final FileUploadDownloadService service;

    @Autowired
    public FileUploadController(FileUploadDownloadService service) {
        this.service = service;
    }

    @GetMapping("/uploadFiles")
    public Iterable<UploadFile> getUploadFileList(){
        return service.getFileList();
    }

    @GetMapping("/uploadFile/{id}")
    public Optional<UploadFile> getUploadFile(@PathVariable Long id){
        return service.getUploadFile(id);
    }
    @PostMapping("/uploadFile")
    public UploadFile uploadFile(@RequestParam("file") MultipartFile file) {
        UploadFile uploadFile = service.storeFile(file);

        return uploadFile;
    }

}
