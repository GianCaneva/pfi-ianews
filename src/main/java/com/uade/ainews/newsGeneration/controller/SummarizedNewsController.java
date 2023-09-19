package com.uade.ainews.newsGeneration.controller;


import com.uade.ainews.newsGeneration.service.SummarizeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//development
//http://localhost:8080/news/user/register?email=gfocaneva@gmail.com&password=1234
@RestController
@RequestMapping("/summarize")
public class SummarizedNewsController {

    @Autowired
    private SummarizeNewsService summarizeNewsService;

    @PostMapping("/register")
    public void generateAiArticle(@RequestParam Integer newsId, @RequestParam String userEmail) {
        summarizeNewsService.summarizeNews(newsId, userEmail);
    }
}
