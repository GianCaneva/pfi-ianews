package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.ArticleResponse;
import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.utils.SummarizeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ArticleService {

    //todo, agrgear la logica de lecutra de nocias
    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    //////////////////////////////// CONSTANTS ////////////////////////////////
    public static final int EXTRA_CHARS_PER_EXTENSION = 100;
    public static final int EXTRA_MARGIN_ARTICLE_EXTENSION = 100;
    public static final int INTEREST_SECTION_INCREMENT_VALUE = 10;
    ///////////////////////////////////////////////////////////////////////////

    public ArticleResponse readAnArticle(String userEmail, Integer newsId, Integer articleWordsExtension){
        User reader = userService.getSpecificUser(userEmail);
        SummarizedNews specificNewsRaw = newsService.getSpecificNews(Long.valueOf(newsId));

        //Add user interest to specific section
        userService.addInterest(userEmail, specificNewsRaw.getSection(), INTEREST_SECTION_INCREMENT_VALUE);
        String title = specificNewsRaw.getTitle();

        //Calculate article length per user
        Integer minArticleExtension = findArticleExtension(reader, specificNewsRaw, articleWordsExtension);
        String articleSummarized = SummarizeArticle.sumUp(String.valueOf(specificNewsRaw), minArticleExtension + EXTRA_MARGIN_ARTICLE_EXTENSION, minArticleExtension);
        return ArticleResponse.builder().title(title).article(articleSummarized).extension(articleWordsExtension).build();
    }

    private Integer findArticleExtension(User reader, SummarizedNews specificNewsRaw, Integer articleWordsExtension) {

        Integer timeToReadSection = 0;
        switch (specificNewsRaw.getSection()){
            case "POLITICS":
                timeToReadSection = reader.getPoliticsTime() + EXTRA_CHARS_PER_EXTENSION * articleWordsExtension;
                break;
            case "ECONOMY":
                timeToReadSection = reader.getEconomyTime() + EXTRA_CHARS_PER_EXTENSION * articleWordsExtension;
                break;
            case "SPORTS":
                timeToReadSection = reader.getSportsTime() + EXTRA_CHARS_PER_EXTENSION * articleWordsExtension;
                break;
            case "SOCIAL":
                timeToReadSection = reader.getSocialTime() + EXTRA_CHARS_PER_EXTENSION * articleWordsExtension;
                break;
            case "INTERNATIONAL":
                timeToReadSection = reader.getInternationalTime() + EXTRA_CHARS_PER_EXTENSION * articleWordsExtension;
                break;
            case "POLICE":
                timeToReadSection = reader.getPoliceTime() + EXTRA_CHARS_PER_EXTENSION * articleWordsExtension;
                break;
            default:
                timeToReadSection = 100 + EXTRA_CHARS_PER_EXTENSION * articleWordsExtension;
                break;
        }

        return timeToReadSection;

    }

}
