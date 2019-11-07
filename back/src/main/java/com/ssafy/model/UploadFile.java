package com.ssafy.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UPLOAD_FILE")
public @Data
class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_file_id")
    private Long id;

    @Column(name="file_name")
    private String fileName;

    @Column(name="size")
    private long size;

    @Column(name="mime_type")
    private String mimeType;

    @CreationTimestamp
    @Column(name="insert_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertDate;

    public UploadFile(String fileName, long size, String contentType) {
        this.fileName = fileName;
        this.size = size;
        this.mimeType = contentType;
    }
}
