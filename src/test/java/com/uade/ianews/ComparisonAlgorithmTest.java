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
        List<String> keywords1 = new LinkedList<String>(Arrays.asList("Sergio Massa", "gabinete Económico", "ministro", "PASO", "medidas"));
        List<String> keywords2 = new LinkedList<String>(Arrays.asList("Sergio Massa", "gabinete Económico", "C", "D", "E"));
        List<String> keywords5 = new LinkedList<String>(Arrays.asList("X", "Y", "Z", "J", "Q"));
        List<String> keywords3 = new LinkedList<String>(Arrays.asList("A", "gabinete Económico", "C", "PASO", "medidas"));
        List<String> keywords4 = new LinkedList<String>(Arrays.asList("A", "B", "C", "D", "E"));
        List<String> keywords6 = new LinkedList<String>(Arrays.asList("E", "A", "F", "D", "B"));
        News news1 = News.builder().keywords(keywords1).build();
        News news2 = News.builder().keywords(keywords2).build();
        News news3 = News.builder().keywords(keywords3).build();
        News news4 = News.builder().keywords(keywords4).build();
        News news5 = News.builder().keywords(keywords5).build();
        News news6 = News.builder().keywords(keywords6).build();

        List<News> allNews= new LinkedList<News>(Arrays.asList(news1, news2, news3, news4, news5, news6));

        List<List<News>> sameNews = ComparisonAlgorithm.identifySameNews(allNews);

        assertThat(sameNews.size()).isEqualTo(2);
        assertThat(sameNews.get(0).size()).isEqualTo(2);
        assertThat(sameNews.get(1).size()).isEqualTo(3);
    }

    @Test
    public void givenMultiplesNewsThereAreNotSimilarOnes() {
        List<String> keywords1 = new LinkedList<String>(Arrays.asList("Sergio Massa", "gabinete Económico", "ministro", "PASO", "medidas"));
        List<String> keywords2 = new LinkedList<String>(Arrays.asList("X", "Y", "Z", "J", "Q"));
        List<String> keywords3 = new LinkedList<String>(Arrays.asList("A", "B", "C", "D", "E"));
        News news1 = News.builder().keywords(keywords1).build();
        News news2 = News.builder().keywords(keywords2).build();
        News news3 = News.builder().keywords(keywords3).build();

        List<News> allNews= new LinkedList<News>(Arrays.asList(news1, news2, news3));

        List<List<News>> sameNews = ComparisonAlgorithm.identifySameNews(allNews);

        assertThat(sameNews.size()).isEqualTo(0);

    }




}