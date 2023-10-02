package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.ArticleResponse;
import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.utils.SummarizeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
@Service
public class ArticleService {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    //////////////////////////////// CONSTANTS ////////////////////////////////
    public static final int EXTRA_CHARS_PER_EXTENSION = 100;
    public static final int EXTRA_MARGIN_ARTICLE_EXTENSION = 100;
    public static final int INTEREST_SECTION_INCREMENT_VALUE = 10;
    public static final int DEFAULT_WORDS_EXTENSION = 100;
    ///////////////////////////////////////////////////////////////////////////

    public ArticleResponse readAnArticle(String userEmail, Integer newsId, Integer articleWordsExtension) {
        User reader = userService.getSpecificUser(userEmail);
        SummarizedNews specificNewsRaw = newsService.getSpecificNews(Long.valueOf(newsId));

        //set user interest to specific section
        userService.addInterest(userEmail, specificNewsRaw.getSection(), INTEREST_SECTION_INCREMENT_VALUE);
        String title = specificNewsRaw.getTitle();

        //Calculate article length per user
        Integer articleExtension = findArticleExtension(reader, specificNewsRaw.getSection(), articleWordsExtension);
        String articleSummarized = SummarizeArticle.sumUp(String.valueOf(specificNewsRaw.getRawArticle()), articleExtension);
        return ArticleResponse.builder().title(title).article(articleSummarized).extension(articleWordsExtension).build();
    }

    private Integer findArticleExtension(User reader, String section, Integer articleWordsExtension) {

        Integer amountOfWordsForArticle = 0;
        switch (section) {
            case "POLITICS":
                amountOfWordsForArticle = calculateWordCount(reader.getPoliticsTime());
                break;
            case "ECONOMY":
                amountOfWordsForArticle = calculateWordCount(reader.getEconomyTime());
                break;
            case "SPORTS":
                amountOfWordsForArticle = calculateWordCount(reader.getSportsTime());
                break;
            case "SOCIAL":
                amountOfWordsForArticle = calculateWordCount(reader.getSocialTime());
                break;
            case "INTERNATIONAL":
                amountOfWordsForArticle = calculateWordCount(reader.getInternationalTime());
                break;
            case "POLICE":
                amountOfWordsForArticle = calculateWordCount(reader.getPoliceTime());
                break;
            default:
                amountOfWordsForArticle = DEFAULT_WORDS_EXTENSION;
                break;
        }

        return amountOfWordsForArticle + EXTRA_CHARS_PER_EXTENSION * articleWordsExtension;
    }

    public Integer calculateWordCount(BigDecimal lectureTime) {
        int wordCount = 0;
        if (lectureTime.compareTo(BigDecimal.ZERO) >= 0 && lectureTime.compareTo(BigDecimal.valueOf(0.5)) <= 0) {
            wordCount = DEFAULT_WORDS_EXTENSION;
        } else if (lectureTime.compareTo(BigDecimal.valueOf(0.5)) > 0 && lectureTime.compareTo(BigDecimal.valueOf(1)) <= 0) {
            wordCount = 200;
        } else if (lectureTime.compareTo(BigDecimal.valueOf(1)) > 0 && lectureTime.compareTo(BigDecimal.valueOf(1.5)) <= 0) {
            wordCount = 300;
        } else if (lectureTime.compareTo(BigDecimal.valueOf(1.5)) > 0) {
            wordCount = 400;
        } else {
            wordCount = DEFAULT_WORDS_EXTENSION;
        }
        return wordCount;
    }
}
