package com.groupone.backoffice.newsGeneration.repository;


import com.groupone.backoffice.newsGeneration.dto.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsGenerationRepository  extends JpaRepository <News, Long>{
}


