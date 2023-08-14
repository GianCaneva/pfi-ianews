package com.uade.ianews;

import junit.framework.TestCase;

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
public class ComparisonAlgorithmTest extends TestCase {
    @Test
    public void givenMultiplesNewsThereAreSimilarOnes() {
        List<String> keywordsFirst = new LinkedList<String>(Arrays.asList("Sergio Massa", "gabinete Económico", "ministro", "PASO", "medidas"));
        List<String> keywordsSecond = new LinkedList<String>(Arrays.asList("Sergio Massa", "gabinete Económico", "C", "D", "E"));
        List<String> keywordsThird = new LinkedList<String>(Arrays.asList("A", "gabinete Económico", "C", "PASO", "medidas"));
        News news1 = News.builder().keywords(keywordsFirst).build();
        News news2 = News.builder().keywords(keywordsSecond).build();
        News news3 = News.builder().keywords(keywordsThird).build();

        List<News> allNews= new LinkedList<News>(Arrays.asList(news1, news2, news3));

        List<List<News>> sameNews = ComparisonAlgorithm.identifySameNews(allNews);

        assertThat(sameNews.size()).isEqualTo(1);

    }

    @Test
    public void givenMultiplesNewsThereAreNotSimilarOnes() {

    }




}