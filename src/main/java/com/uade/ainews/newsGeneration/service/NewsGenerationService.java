package com.uade.ainews.newsGeneration.service;


import com.uade.ainews.TestUtils;
import com.uade.ainews.newsGeneration.dto.News;
import com.uade.ainews.newsGeneration.repository.NewsGenerationRepository;
import com.uade.ainews.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class NewsGenerationService {

    public static final String CLARIN_RSS = "https://www.clarin.com/rss/lo-ultimo/";
    public static final String PERFIL_RSS = "https://www.perfil.com/feed";
    @Autowired
    private NewsGenerationRepository newsGenerationRepository;

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
            for(int i = 0; i<allSiblingNews.size(); i++){
                StringBuilder mergeSiblingArticles = new StringBuilder();
                List<News> siblings = allSiblingNews.get(i);
                for (int j = 0; j<siblings.size(); j++){
                    mergeSiblingArticles.append(siblings.get(j).getArticle()).append(" ");
                }
                //remove bias
                String mergeArticleWithoutBias = BiasRemover.remove(String.valueOf(mergeSiblingArticles));
                //call to AI to resume
                String siblingNewsSummarized = SummarizeArticle.sumUp(mergeArticleWithoutBias);
                //remove bias
                String summarizationWithoutBias = BiasRemover.remove(siblingNewsSummarized);
                System.out.println("-------------------------------------------------");
                System.out.println(summarizationWithoutBias);
                response.add(siblingNewsSummarized);
            }
            return response;

    }

    private void saveNewsOntoDB(List<List<News>> allSiblingNews) {
        for(int i = 0; i<allSiblingNews.size();i++){
            List<News> aGroupOfSibling = allSiblingNews.get(i);
            for(int j = 0; j<aGroupOfSibling.size();j++){
                newsGenerationRepository.save(aGroupOfSibling.get(j));
            }

        }
    }
}
