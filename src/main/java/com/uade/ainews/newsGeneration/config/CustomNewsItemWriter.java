package com.uade.ainews.newsGeneration.config;

import com.uade.ainews.newsGeneration.dto.News;
import com.uade.ainews.newsGeneration.repository.NewsGenerationRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomNewsItemWriter implements ItemWriter<News> {

    @Value("#{stepExecutionContext['allLinks']}")
    private List<List<News>> allLinks;

    private NewsGenerationRepository newsGenerationRepository;
    @Autowired
    public CustomNewsItemWriter(NewsGenerationRepository newsGenerationRepository) {
        this.newsGenerationRepository = newsGenerationRepository;
    }
    @Override
    public void write(Chunk<? extends News> chunk) throws Exception {
        for(int i = 0; i<allLinks.size();i++){
            List<News> aGroupOfSibling = allLinks.get(i);
            for(int j = 0; j<aGroupOfSibling.size();j++){
                newsGenerationRepository.save(aGroupOfSibling.get(j));
            }
        }
    }
}
