package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.repository.SummarizedNewsRepository;
import com.uade.ainews.newsGeneration.repository.UserRepository;
import com.uade.ainews.newsGeneration.utils.SummarizeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SummarizeNewsService {

    public static final int EXTRA_ARTICLE_EXTENSION = 100;
    @Autowired
    private SummarizedNewsRepository summarizedNewsRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferNewsService offerNewsService;

    @Autowired
    private UserService userService;

    public void summarizeNews(Integer newsId, String userEmail){

        Optional<SummarizedNews> newsById = summarizedNewsRepository.findById(Long.valueOf(newsId));

        if (newsById.isPresent()) {
            SummarizedNews allSameNewsRaw = newsById.get();
            //resume text and eliminate bias
            Integer minArticleExtension = findArticleExtension(allSameNewsRaw, userEmail);
            String siblingNewsSummarizedAndBiasFree= SummarizeArticle.sumUp(String.valueOf(allSameNewsRaw), minArticleExtension + EXTRA_ARTICLE_EXTENSION, minArticleExtension);

            //remove bias
            System.out.println("summarizationWithoutBias:" + siblingNewsSummarizedAndBiasFree);
            System.out.println("-------------------------------------------------");
            /*response.add(siblingNewsSummarized);
            summarizedNews.add(SummarizedNews.builder().summary(siblingNewsSummarized).releaseDate(LocalDateTime.now()).build());

            saveSummarizedNewsOntoDB(summarizedNews);
            System.out.println("/////////////////////////");
            System.out.println("FINALIZACION DE LA EJECUCION");
            System.out.println("/////////////////////////");
            return response;
            */

        }else{
            //todo not present
        }
    }

    private Integer findArticleExtension(SummarizedNews allSameNewsRaw, String userEmail) {
        User reader = userRepository.findByEmail(userEmail);

        Integer timeToReadSection = 0;
        switch (allSameNewsRaw.getSection()){
            case "POLITICS":
                timeToReadSection = reader.getPoliticsTime();
                break;
            case "ECONOMY":
                timeToReadSection = reader.getEconomyTime();
                break;
            case "SPORTS":
                timeToReadSection = reader.getSportsTime();
                break;
            case "SOCIAL":
                timeToReadSection = reader.getSocialTime();
                break;
            case "INTERNATIONAL":
                timeToReadSection = reader.getInternationalTime();
                break;
            case "POLICE":
                timeToReadSection = reader.getPoliceTime();
                break;
            default:
                timeToReadSection = 100;
                break;
        }
        return timeToReadSection;

    }

    private void saveSummarizedNewsOntoDB(List<SummarizedNews> allSummarizedNews) {
        for(int j = 0; j<allSummarizedNews.size();j++){
            summarizedNewsRepository.save(allSummarizedNews.get(j));
        }
    }

    public Page<SummarizedNews> getNews(String userEmail, PageRequest pageRequest) {
        return offerNewsService.getNews(userEmail, pageRequest);
    }

    public Page<SummarizedNews> getNewsBySection(String userEmail, String section, PageRequest pageRequest) {
        return offerNewsService.getNewsBySection(userEmail, section, pageRequest);
    }




}
