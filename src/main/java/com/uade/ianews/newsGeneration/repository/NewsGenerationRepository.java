package com.uade.ianews.newsGeneration.repository;

import com.uade.ianews.newsGeneration.dto.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsGenerationRepository  extends JpaRepository <News, Long>{
}


