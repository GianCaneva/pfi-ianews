package com.uade.ainews.newsGeneration.controller;
import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.service.SummarizeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//development
//http://localhost:8080/news/user/register?email=gfocaneva@gmail.com&password=1234
@RestController
@RequestMapping("/news")
public class SummarizedNewsController {

    @Autowired
    private SummarizeNewsService summarizeNewsService;

    @GetMapping("/home")
    public ResponseEntity<Page<SummarizedNews>> getFeed(
            @RequestParam(name = "user", required = true) String userEmail,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<SummarizedNews> newsPage = summarizeNewsService.getNews(userEmail, pageRequest);
        return ResponseEntity.ok(newsPage);
    }

    @GetMapping("/section")
    public ResponseEntity<Page<SummarizedNews>> getSection(
            @RequestParam(name = "user", required = true) String userEmail,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<SummarizedNews> newsPage = summarizeNewsService.getNews(userEmail, pageRequest);
        return ResponseEntity.ok(newsPage);
    }


    @GetMapping("/generate")
    public void generateAiArticle(@RequestParam Integer newsId, @RequestParam String userEmail) {
        summarizeNewsService.summarizeNews(newsId, userEmail);
        //agregarle el step
    }


}