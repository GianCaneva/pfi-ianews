package com.uade.ainews;


import com.uade.ainews.newsGeneration.service.NewsGenerationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public static void main2(String[] args) throws IOException {
        NewsGenerationService newsGenerationService = new NewsGenerationService();
//          newsGenerationService.lookAndGenerateNews();
        newsGenerationService.test();
    }
}
