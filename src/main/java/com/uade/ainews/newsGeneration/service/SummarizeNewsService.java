package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.repository.SummarizedNewsRepository;
import com.uade.ainews.newsGeneration.repository.UserRepository;
import com.uade.ainews.utils.BiasRemover;
import com.uade.ainews.utils.SummarizeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SummarizeNewsService {

    @Autowired
    private SummarizedNewsRepository summarizedNewsRepository;
    @Autowired
    private UserRepository userRepository;

    public void summarizeNews(Integer newsId, String userEmail){

        Optional<SummarizedNews> newsById = summarizedNewsRepository.findById(Long.valueOf(newsId));

        if (newsById.isPresent()) {
            SummarizedNews allSameNewsRaw = newsById.get();
            //remove bias
            String mergeArticleWithoutBias = BiasRemover.remove(String.valueOf(allSameNewsRaw));

            //call to AI to resume
            Integer articleExtension = findArticleExtension(allSameNewsRaw, userEmail);
            String siblingNewsSummarized = SummarizeArticle.sumUp(mergeArticleWithoutBias, articleExtension);

            //remove bias
            String summarizationWithoutBias = BiasRemover.remove(siblingNewsSummarized);
            System.out.println("summarizationWithoutBias:" + summarizationWithoutBias);
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
}
