package com.uade.ainews.newsGeneration.controller;

import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//development
//http://localhost:8080/news/user/register?email=gfocaneva@gmail.com&password=1234
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/home")
    public ResponseEntity<Page<SummarizedNews>> getFeed(
            @RequestParam(name = "userId", required = true) Long userId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<SummarizedNews> newsPage = newsService.getHomeNews(userId, pageRequest);
        return ResponseEntity.ok(newsPage);
    }

    @GetMapping("/section")
    public ResponseEntity<Page<SummarizedNews>> getSection(
            @RequestParam(name = "userId", required = true) Long userId,
            @RequestParam(name = "section", required = false) String section,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        Page<SummarizedNews> newsPage = newsService.getNewsBySection(userId, section, pageRequest);
        return ResponseEntity.ok(newsPage);
    }

    // Endpoint to test the connection
    @GetMapping("/healthcheck")
    public ResponseEntity<Object> login() {
        try {
            return ResponseEntity.ok("Unchained News application (middleware) is up on Google Cloud Platform successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}