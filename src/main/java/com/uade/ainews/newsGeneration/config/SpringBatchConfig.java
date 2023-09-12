package com.uade.ainews.newsGeneration.config;

import com.uade.ainews.newsGeneration.dto.News;
import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.repository.NewsGenerationRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.Job;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.List;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private NewsGenerationRepository newsGenerationRepository;

    @Bean
    public ItemReader<List<String>> reader() {
        return new CustomNewsItemReader();
    }

    @Bean
    public CustomNewsItemProcessor processor() {
        return new CustomNewsItemProcessor();
    }

    @Bean
    public CustomNewsItemWriter writer() {
        return new CustomNewsItemWriter(newsGenerationRepository);
    }

    @Bean
    public Step myStep(ItemReader<List<String>> customItemReader, CustomNewsItemProcessor customItemProcessor, CustomNewsItemWriter writer) {
        return stepBuilderFactory.get("myStep")
                .<List<String>, List<List<News>>>chunk(1) // Define el tamaño del chunk
                .reader(customItemReader)
                .processor(customItemProcessor) // Cambia aquí para usar customItemProcessor
                .writer(writer)
                .taskExecutor(taskExecutor()) // Usa el TaskExecutor que definiste
                .build();
    }

    @Bean
    public Job runJob(Step myStep) {
        return jobBuilderFactory.get("myJob")
                .start(myStep)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }


}
