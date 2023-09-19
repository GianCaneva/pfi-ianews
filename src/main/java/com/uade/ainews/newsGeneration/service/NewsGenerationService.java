package com.uade.ainews.newsGeneration.service;


import com.uade.ainews.newsGeneration.dto.News;
import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.repository.NewsGenerationRepository;
import com.uade.ainews.newsGeneration.repository.SummarizedNewsRepository;
import com.uade.ainews.newsGeneration.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Deprecated
public class NewsGenerationService {

    public static final String CLARIN_RSS = "https://www.clarin.com/rss/lo-ultimo/";
    public static final String PERFIL_RSS = "https://www.perfil.com/feed";
    @Autowired
    private NewsGenerationRepository newsGenerationRepository;
    @Autowired
    private SummarizedNewsRepository summarizedNewsRepository;

    public Optional<News> test(){
        Optional<News> newsById = newsGenerationRepository.findById(1L);
        if (newsById.isEmpty()){
            System.out.println("NOT");
        }else{
            System.out.println("YES");
        }
        return newsById;

    }

    public List<String> lookAndGenerateNews() throws IOException {

        try {
            List<String> allRSSLinks = new LinkedList<>();
            allRSSLinks.add(CLARIN_RSS);
            allRSSLinks.add(PERFIL_RSS);
            List<String> allLinks = new LinkedList<>();
            for(int i=0; i<allRSSLinks.size(); i++){
                allLinks.addAll(SsrReader.getAllLinks(allRSSLinks.get(i)));
            }

            List<News> allNewsWithInfo = new LinkedList<>();
            //todo modificar para que use todos los elementos de la lista y no solo 30
            for(int i=30; i<allLinks.size(); i++){
                //Scrapping. Get article and title from a URL
                News newsWithInformationFromPagAndKeyWords = WebScrapper.getInformationFromPage(News.builder().url(allLinks.get(i)).build());
                //Get keywords
                List<String> keyWords = KeywordFinder.getKeyWords(newsWithInformationFromPagAndKeyWords.getArticle());
                newsWithInformationFromPagAndKeyWords.setKeywords(keyWords);
                allNewsWithInfo.add(newsWithInformationFromPagAndKeyWords);
            }
            //Match same news
            List<List<News>> allSiblingNews = ComparisonAlgorithm.identifySameNews(allNewsWithInfo);
            //Save on the db
            saveNewsOntoDB(allSiblingNews);
            //Summarize same news
            List<String> response = new ArrayList<>();
            List<SummarizedNews> summarizedNews = new ArrayList<>();
            for(int i = 0; i<allSiblingNews.size(); i++){
                StringBuilder mergeSiblingArticles = new StringBuilder();
                List<News> siblings = allSiblingNews.get(i);
                for (int j = 0; j<siblings.size(); j++){
                    mergeSiblingArticles.append(siblings.get(j).getArticle()).append(" ");
                }
                //call to AI to resume and eliminate Bias
                String siblingNewsSummarized = SummarizeArticle.sumUp(String.valueOf(mergeSiblingArticles), 100, 200);
                System.out.println("summarizationWithoutBias:" + siblingNewsSummarized);
                System.out.println("-------------------------------------------------");
                response.add(siblingNewsSummarized);
                summarizedNews.add(SummarizedNews.builder().summary(siblingNewsSummarized).releaseDate(LocalDateTime.now()).build());
            }
            saveSummarizedNewsOntoDB(summarizedNews);
            System.out.println("/////////////////////////");
            System.out.println("FINALIZACION DE LA EJECUCION");
            System.out.println("/////////////////////////");
            return response;
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    private void saveNewsOntoDB(List<List<News>> allSiblingNews) {
        for(int i = 0; i<allSiblingNews.size();i++){
            List<News> aGroupOfSibling = allSiblingNews.get(i);
            for(int j = 0; j<aGroupOfSibling.size();j++){
                newsGenerationRepository.save(aGroupOfSibling.get(j));
            }
        }
    }

    private void saveSummarizedNewsOntoDB(List<SummarizedNews> allSummarizedNews) {
        for(int j = 0; j<allSummarizedNews.size();j++){
            summarizedNewsRepository.save(allSummarizedNews.get(j));
        }
    }
}
