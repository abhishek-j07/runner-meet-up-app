package com.app.runnermeet.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubDTO {

    private long id;

    private String title;

    private String photoUrl;

    private String content;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
