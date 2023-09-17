package com.uade.ainews.newsGeneration.controller;


import com.uade.ainews.newsGeneration.service.SummarizeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//development
//http://localhost:8080/news/user/register?email=gfocaneva@gmail.com&password=1234
@RestController
@RequestMapping("/summarize")
public class SummarizedNewsController {

    @Autowired
    private SummarizeNewsService summarizeNewsService;

    @PostMapping("/register")
    public void registerUser(@RequestParam Integer newsId, @RequestParam String userEmail) {
        summarizeNewsService.summarizeNews(newsId, userEmail);
    }
}
