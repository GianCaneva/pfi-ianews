package com.uade.ainews.newsGeneration.repository;


import com.uade.ainews.newsGeneration.dto.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsGenerationRepository  extends JpaRepository <News, Long>{
}


