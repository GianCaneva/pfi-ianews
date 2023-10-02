package com.uade.ainews.newsGeneration.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private String title;
    private String article;
    private Integer extension;

}