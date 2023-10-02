package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UserService userService;

    //////////////////////////////// CONSTANTS ////////////////////////////////
    public static final int INTEREST_SECTION_INCREMENT_VALUE = 5;
    ///////////////////////////////////////////////////////////////////////////

    public SummarizedNews getSpecificNews(Long newsId) {
        return newsRepository.findById(newsId).orElseThrow(() -> new NoSuchElementException("News not found: " + newsId));
    }

    private Page<SummarizedNews> getAllCurrentNews(PageRequest pageRequest){
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        //Only returns news created last 24hs
        //return newsRepository.findAllCreatedWithinLast24Hours(twentyFourHoursAgo, pageRequest);
        //Returns all news created
        return newsRepository.findAll(pageRequest);
    }

    public Page<SummarizedNews> getHomeNews(String userEmail, PageRequest pageRequest) {
        User specificUser = userService.getSpecificUser(userEmail);
        return filterByInterest(specificUser, pageRequest);
    }

    public Page<SummarizedNews> getNewsBySection(String userEmail, String section, PageRequest pageRequest) {
        userService.addInterest(userEmail, section, INTEREST_SECTION_INCREMENT_VALUE);
        return newsRepository.findBySection(section, pageRequest);
    }

    private Page<SummarizedNews> filterByInterest(User reader, PageRequest pageRequest) {
        Page<SummarizedNews> allCurrentNews = getAllCurrentNews(pageRequest);
        Map<String, Integer> sectionCount = new HashMap<>(); // Map to keep track of the news count by section
        Map<String, Integer> notShownSectionCount = new HashMap<>(); // Map to keep track of news not shown by section
        List<SummarizedNews> filteredNews = new ArrayList<>();

        for (SummarizedNews news : allCurrentNews) {
            String section = news.getSection();
            int interestThreshold = calculateInterestThreshold(reader, section);

            // Check if enough news for that sections has been shown
            int countShown = sectionCount.getOrDefault(section, 0);
            int countNotShown = notShownSectionCount.getOrDefault(section, 0);

            if (countShown < interestThreshold) {
                filteredNews.add(news);
                sectionCount.put(section, countShown + 1);
            }else {
                notShownSectionCount.put(section, countNotShown + 1);
                if (countShown + countShown == interestThreshold){
                    sectionCount.put(section, 0);
                    notShownSectionCount.put(section, 0);
                }
            }
        }
        int startIndex = pageRequest.getPageNumber() * pageRequest.getPageSize();
        int endIndex = Math.min(startIndex + pageRequest.getPageSize(), filteredNews.size());
        return new PageImpl<>(filteredNews.subList(startIndex, endIndex), pageRequest, filteredNews.size());
    }

    private int calculateInterestThreshold(User reader, String section) {
        // Obtain user interest in the specific section
        int userInterest = getUserInterestInSection(reader, section);

        // Calculate the interest threshold based on the user's interest in section
        int threshold;
        if (userInterest >= 90) {
            threshold = 9;
        } else if (userInterest >= 80) {
            threshold = 8;
        } else if (userInterest >= 70) {
            threshold = 7;
        } else if (userInterest >= 60) {
            threshold = 6;
        } else if (userInterest >= 50) {
            threshold = 5;
        } else if (userInterest >= 40) {
            threshold = 4;
        } else if (userInterest >= 30) {
            threshold = 3;
        } else if (userInterest >= 20) {
            threshold = 2;
        } else if (userInterest >= 10) {
            threshold = 1;
        } else {
            threshold = 1;
        }

        return threshold;
    }

    private int getUserInterestInSection(User reader, String section) {
        Integer interestInSection = 0;
        switch (section){
            case "POLITICS":
                interestInSection = reader.getPoliticsInterest();
                break;
            case "ECONOMY":
                interestInSection = reader.getEconomyInterest();
                break;
            case "SPORTS":
                interestInSection = reader.getSportsInterest();
                break;
            case "SOCIAL":
                interestInSection = reader.getSocialInterest();
                break;
            case "INTERNATIONAL":
                interestInSection = reader.getInternationalInterest();
                break;
            case "POLICE":
                interestInSection = reader.getPoliceInterest();
                break;
            default:
                interestInSection = 100;
                break;
        }
        return interestInSection;
    }



}
