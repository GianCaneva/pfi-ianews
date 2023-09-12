package com.uade.ainews.newsGeneration.config;

import com.uade.ainews.newsGeneration.dto.News;
import com.uade.ainews.utils.ComparisonAlgorithm;
import com.uade.ainews.utils.KeywordFinder;
import com.uade.ainews.utils.WebScrapper;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@StepScope
public class CustomNewsItemProcessor implements ItemProcessor<String, List<List<News>>> {

    @Value("#{stepExecutionContext['allLinks']}")
    private List<String> allLinks;

    @Value("#{stepExecution}")
    private StepExecution stepExecution;

    @Override
    public List<List<News>> process(String item) throws Exception {
        List<News> allNewsWithInfo = new LinkedList<>();
        for (int i = 30; i < allLinks.size(); i++) {
            // Scrapping. Get article and title from a URL
            News newsWithInformationFromPage = WebScrapper.getInformationFromPage(News.builder().url(allLinks.get(i)).build());

            // Get keywords
            List<String> keyWords = KeywordFinder.getKeyWords(newsWithInformationFromPage.getArticle());
            newsWithInformationFromPage.setKeywords(keyWords);

            allNewsWithInfo.add(newsWithInformationFromPage);
        }

        // Match same news
        List<List<News>> allSiblingNews = ComparisonAlgorithm.identifySameNews(allNewsWithInfo);
        stepExecution.getExecutionContext().put("allSiblingNews", allSiblingNews);

        return allSiblingNews;
    }
}
