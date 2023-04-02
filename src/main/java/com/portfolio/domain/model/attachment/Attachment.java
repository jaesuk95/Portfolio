package com.portfolio.domain.model.attachment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String filePath;
    private boolean thumbnailCreated;
    private String publicUrl;
    private boolean publicImage;
    private String fileType;
    private boolean activation;

    @Builder
    public Attachment(String fileName, String filePath, boolean thumbnailCreated, String publicUrl, boolean publicImage, String fileType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.thumbnailCreated = thumbnailCreated;
        this.publicUrl = publicUrl;
        this.publicImage = publicImage;
        this.fileType = fileType;
        this.activation = false;
    }
}
