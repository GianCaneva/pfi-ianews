package com.uade.ianews.dto;


import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String title;
    private LocalDateTime lastGeneratedTime;
    private String body;
}
