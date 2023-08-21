package com.uade.ianews.newsGeneration.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "news")
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String url;
    @Transient
    private List<String> keywords;
    @Column
    private String section;
    @Column
    private String title;
    @Column
    private String article;
    @Column
    private String summary;
    @Column
    private LocalDateTime releaseDate;
    /*
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
*/
    public void setArticle(StringBuilder article) {
        this.article = String.valueOf(article);
    }
}
