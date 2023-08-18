package com.robb.statisticsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robb.statisticsservice.model.ShortenedLinkStatistics;

public interface StatisticsRepository extends JpaRepository<ShortenedLinkStatistics, Long>{
    
}
