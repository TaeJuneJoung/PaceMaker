package com.ssafy.service;

import com.ssafy.exception.FileDownloadException;
import com.ssafy.exception.FileUploadException;
import com.ssafy.model.UploadFile;
import com.ssafy.repository.UploadFileRepository;
import com.ssafy.utility.HashEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class FileUploadDownloadService {

    private final Path fileLocation;
    private final UploadFileRepository uploadFileRepository;
    private final HashEncoder hashEncoder;

    @Autowired
    public FileUploadDownloadService(UploadFileRepository uploadFileRepository, HashEncoder hashEncoder) {
        this.fileLocation = Paths.get("./front/static/images")
                .toAbsolutePath().normalize();
        this.uploadFileRepository = uploadFileRepository;
        this.hashEncoder = hashEncoder;

        try {
            Files.createDirectories(this.fileLocation);
        }catch(Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    public UploadFile storeFile(MultipartFile file) throws NoSuchAlgorithmException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.KOREA);
        String dateToString = dateFormat.format(new Timestamp(System.currentTimeMillis()));
        dateToString.concat(StringUtils.cleanPath(file.getOriginalFilename()));
        String hash = hashEncoder.sha1(dateToString);
        String fileName = hash.concat(".".concat(file.getContentType().split("/")[1]));

        try {
            // 파일명에 부적합 문자가 있는지 확인한다.
            if(fileName.contains(".."))
                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);


            Path targetLocation = this.fileLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            UploadFile uploadFile = new UploadFile(fileName, file.getSize(), file.getContentType());
            uploadFileRepository.save(uploadFile);

            return uploadFile;
        }catch(Exception e) {
            throw new FileUploadException("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하십시오.",e);
        }
    }

}
