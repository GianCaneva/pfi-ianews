package com.uade.ainews.newsGeneration.repository;


import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface NewsRepository extends JpaRepository <SummarizedNews, Long>{

    @Query("SELECT s FROM sumnews s WHERE s.releaseDate >= :twentyFourHoursAgo")
    Page<SummarizedNews> findAllCreatedWithinLast24Hours(LocalDateTime twentyFourHoursAgo, Pageable pageable);

    Page<SummarizedNews> findBySection(String section, Pageable pageable);

}


