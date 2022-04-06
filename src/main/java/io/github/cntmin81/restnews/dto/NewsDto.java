package io.github.cntmin81.restnews.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime postStartDate;
    private LocalDateTime postEndDate;
}
