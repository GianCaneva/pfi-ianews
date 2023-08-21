package com.uade.ianews.newsGeneration.controller;

import com.uade.ianews.newsGeneration.dto.News;
import com.uade.ianews.newsGeneration.service.NewsGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsGenerationController {

    @Autowired
    private NewsGenerationService newsGenerationService;

    @GetMapping("/all")
    public ResponseEntity<?> getNews() {
        Optional<News> news = newsGenerationService.test();
        if (news.isPresent()) {
            return ResponseEntity.ok(news.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"No news found\"}");
        }
    }

}
