package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.News;
import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.repository.NewsGenerationRepository;
import com.uade.ainews.newsGeneration.repository.SummarizedNewsRepository;
import com.uade.ainews.utils.ComparisonAlgorithm;
import com.uade.ainews.utils.KeywordFinder;
import com.uade.ainews.utils.SsrReader;
import com.uade.ainews.utils.WebScrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class NewsGetterService {

    public static final String CLARIN_RSS = "https://www.clarin.com/rss/lo-ultimo/";
    public static final String PERFIL_RSS = "https://www.perfil.com/feed";
    @Autowired
    private SummarizedNewsRepository summarizedNewsRepository;

    public void getSameNews() throws IOException {

        try {
            List<String> allRSSLinks = new LinkedList<>();
            allRSSLinks.add(CLARIN_RSS);
            allRSSLinks.add(PERFIL_RSS);
            List<String> allLinks = new LinkedList<>();
            for (int i = 0; i < allRSSLinks.size(); i++) {
                allLinks.addAll(SsrReader.getAllLinks(allRSSLinks.get(i)));
            }

            List<News> allNewsWithInfo = new LinkedList<>();
            //todo modificar para que use todos los elementos de la lista y no solo 30
            for (int i = 30; i < allLinks.size(); i++) {
                //Scrapping. Get article and title from a URL
                News newsWithInformationFromPagAndKeyWords = WebScrapper.getInformationFromPage(News.builder().url(allLinks.get(i)).build());
                //Get keywords
                List<String> keyWords = KeywordFinder.getKeyWords(newsWithInformationFromPagAndKeyWords.getArticle());
                newsWithInformationFromPagAndKeyWords.setKeywords(keyWords);
                allNewsWithInfo.add(newsWithInformationFromPagAndKeyWords);
            }
            //Identify and match same news
            List<List<News>> allSiblingNews = ComparisonAlgorithm.identifySameNews(allNewsWithInfo);

            //Merge same articles onto a new one
            for(int i = 0; i<allSiblingNews.size(); i++){
                StringBuilder mergeSiblingArticles = new StringBuilder();
                List<News> siblings = allSiblingNews.get(i);
                for (int j = 0; j<siblings.size(); j++){
                    mergeSiblingArticles.append(siblings.get(j).getArticle()).append(" ");
                }
                //Save on the db
                summarizedNewsRepository.save(SummarizedNews.builder().rawArticle(String.valueOf(mergeSiblingArticles)).releaseDate(LocalDateTime.now()).build());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

