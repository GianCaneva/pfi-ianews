package com.uade.ianews;

import com.uade.ianews.dto.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
public class Test1 {


    @Test
    public void homeResponse() {
        List<String> keywordsFirst = new LinkedList<String>(Arrays.asList("Sergio Massa", "gabinete Económico", "ministro", "PASO", "medidas"));
        List<String> keywordsSecond = new LinkedList<String>(Arrays.asList("Sergio Massa", "gabinete Económico", "C", "D", "E"));
        List<String> keywordsThird = new LinkedList<String>(Arrays.asList("A", "gabinete Económico", "C", "PASO", "medidas"));
        
        //reemplazasr acentos, hacer trim para eliminar espacios y comas y poner to do en mayuscula
        
        News news1 = News.builder().keywords(keywordsFirst).build();
        News news2 = News.builder().keywords(keywordsSecond).build();
        News news3 = News.builder().keywords(keywordsThird).build();

        List<News> allNews= new LinkedList<News>(Arrays.asList(news1, news2, news3));
        List<List<News>> matchNews = new LinkedList<>();

        int matches = 0;

        for (int i=0; i<allNews.size(); i++){
            List<News> siblingNews = new LinkedList<News>();
            News currentNews = allNews.get(i);
            List<String> keywordsFirst1 = currentNews.getKeywords();
            for (int j=1; j<allNews.size()-i; j++){
                News comparisonNews = allNews.get(i+1);
                List<String> keywordsSecond2 = comparisonNews.getKeywords();

                for (int x=0; x<keywordsFirst1.size(); x++){
                    String keywords1 = keywordsFirst1.get(x);
                    for (int y=0; y<keywordsSecond2.size(); y++){
                        String keywords2 = keywordsFirst1.get(y);
                        if (keywords1.equals(keywords2)){
                            matches++;
                            if (matches == 3){
                                //currentNews.addSameNews(comparisonNews);
                                siblingNews.add(comparisonNews);
                                allNews.remove(comparisonNews);
                            }
                            break;
                        }
                    }
                }

            }
            matches = 0;
            if (!siblingNews.isEmpty()){
                siblingNews.add(currentNews);
                matchNews.add(siblingNews);
            }

        }



        assertThat("a").isEqualTo("Spring is here!");
    }
}
