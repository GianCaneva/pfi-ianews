package com.uade.ianews.dto;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private String id;
    private String url;
    private List<String> keywords;
    private String title;
    private String article;
    private String summary;
    private List<News> sameNews;

    public void addSameNews(News newNews){
        sameNews.add(newNews);
    }

    public void setArticle(StringBuilder article) {
        this.article = String.valueOf(article);
    }
}
