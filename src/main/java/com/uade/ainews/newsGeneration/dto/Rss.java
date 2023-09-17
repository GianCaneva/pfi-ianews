package com.uade.ainews.newsGeneration.dto;



import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rss {

    private String url;
    private String section;



}
