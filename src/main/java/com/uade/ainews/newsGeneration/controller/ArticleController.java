package com.uade.ainews.newsGeneration.controller;

import com.uade.ainews.newsGeneration.dto.ArticleResponse;
import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.service.ArticleService;
import com.uade.ainews.newsGeneration.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    //todo tiempo de lecutra
    @Autowired
    private ArticleService articleService;


    @GetMapping("/get")
    public ResponseEntity<ArticleResponse> getSection(
            @RequestParam(name = "user") String userEmail,
            @RequestParam(name = "newsId") Integer newsId,
            @RequestParam(name = "extension", defaultValue = "0") Integer extension
    ) {
        //If extension parameter is lower than 0 or grater than 3, sets 0 as default
        extension = (extension >= 0 && extension <= 3) ? extension : 0;

        ArticleResponse newsSummarized = articleService.readAnArticle(userEmail, newsId, extension);
        return ResponseEntity.ok(newsSummarized);
    }

}
