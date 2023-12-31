package com.uade.ainews.newsGeneration.controller;

import com.uade.ainews.newsGeneration.dto.ArticleResponse;
import com.uade.ainews.newsGeneration.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/getArticle")
    public ResponseEntity<ArticleResponse> getArticle(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "newsId") Integer newsId,
            @RequestParam(name = "extension", defaultValue = "0") Integer extension
    ) {
        // If extension parameter is lower than 0 or grater than 3, sets 0 as default
        extension = (extension >= 0 && extension <= 3) ? extension : 0;

        ArticleResponse newsSummarized = articleService.readAnArticle(userId, newsId, extension);
        return ResponseEntity.ok(newsSummarized);
    }
}
