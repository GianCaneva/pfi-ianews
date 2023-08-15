package com.uade.ianews;

import com.uade.ianews.dto.News;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NewsGenerationService {

    public static final String CLARIN_RSS = "https://www.clarin.com/rss/lo-ultimo/";
    public static final String PERFIL_RSS = "https://www.perfil.com/feed";

    public static void main(String[] args) throws IOException {
        List<String> allRSSLinks = new LinkedList<>();
        allRSSLinks.add(CLARIN_RSS);
        allRSSLinks.add(PERFIL_RSS);
        List<String> allLinks = new LinkedList<>();
        for(int i=0; i<allRSSLinks.size(); i++){
            allLinks.addAll(SsrReader.getAllLinks(allRSSLinks.get(i)));
        }

        List<News> allNewsWithInfo = new LinkedList<>();
        for(int i=30; i<allLinks.size(); i++){
            //Scrapping. Get article and title from a URL
            News newsWithInformationFromPagAndKeyWords = WebScrapper.getInformationFromPage(News.builder().url(allLinks.get(i)).build());
            //Get keywords
            List<String> keyWords = KeywordFinder.getKeyWords(newsWithInformationFromPagAndKeyWords.getArticle());
            newsWithInformationFromPagAndKeyWords.setKeywords(keyWords);
            allNewsWithInfo.add(newsWithInformationFromPagAndKeyWords);
        }
        //Match same news
        List<List<News>> lists = ComparisonAlgorithm.identifySameNews(allNewsWithInfo);
        String h="hola";



    }
}
