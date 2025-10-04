package com.app.runnermeet.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubDTO {

    private long id;

    @NotEmpty(message = "Club Title should not be empty")
    private String title;

    @NotEmpty(message = "Club Photo should not be empty")
    private String photoUrl;

    @NotEmpty(message = "Content should not be empty")
    private String content;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
