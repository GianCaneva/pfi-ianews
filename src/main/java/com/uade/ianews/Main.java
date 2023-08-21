package com.uade.ianews;

import com.uade.ianews.newsGeneration.service.NewsGenerationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
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
