package com.uade.ianews;
import com.uade.ianews.dto.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WebScrapper {

    public static void main(String[] args) throws IOException {
        List<News> allNews= new LinkedList<News>();
        //String url = "https://www.pagina12.com.ar/578331-zielinski-planea-el-debut-de-isla-en-copa-argentina";
        String url = "https://www.clarin.com/deportes/oficializo-programacion-primera-fecha-copa-liga-profesional_0_1pagyrd6z0.html";
        if (url.substring(0, 22).contains("clarin")){
            allNews.add(scrapperClarin(url));
        }else{
            allNews.add(scrapperPagina12(url));
        }

    }
    //

    private static News scrapperClarin(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        //Title
        Elements headline = document.select("h1");
        Elements subheaderGeneral = document.select("div.title.check-space");
        Elements subheaderTag = subheaderGeneral.select("h2");
        String title = headline.text();
        String subtitle = subheaderTag.text();

        //Article body
        Elements body = document.select("article.entry-body");
        StringBuilder article = new StringBuilder();
        article.append(subtitle);
        for (Element partOfBody : body) {
            article.append(partOfBody.text());
        }
        System.out.println("Article: " + article);
        return News.builder().url(url).title(title).article(article.toString()).build();
    }

    private static News scrapperPagina12(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        //Title
        Elements headline = document.select("h1");
        String title = headline.text();

        //Article body
        Elements body = document.select("div.article-main-content.article-text");
        StringBuilder article = new StringBuilder();
        for (Element partOfBody : body) {
            article.append(partOfBody.text());
        }

        return News.builder().url(url).title(title).article(article.toString()).build();
    }
}


