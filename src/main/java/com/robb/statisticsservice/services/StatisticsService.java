package com.robb.statisticsservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robb.statisticsservice.model.ShortenedLinkStatistics;
import com.robb.statisticsservice.repositories.StatisticsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StatisticsService {
    private static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    StatisticsRepository statisticsRepository;

    public ShortenedLinkStatistics updateOrCreateStatistics(Long id) {
        return statisticsRepository.findById(id)
            .map(existingStatistics -> {
                existingStatistics.increaseHits();
                logger.info("Updating statistics for id {}", existingStatistics.getId());
                return statisticsRepository.save(existingStatistics);
            })
            .orElseGet(() -> {
                ShortenedLinkStatistics shortenedLinkStatistics = new ShortenedLinkStatistics();
                shortenedLinkStatistics.setId(id);
                shortenedLinkStatistics.setHits(1);
                logger.info("Creating new statistics entry for id {}", shortenedLinkStatistics.getId());
                return statisticsRepository.save(shortenedLinkStatistics);
            });
    }

    public ShortenedLinkStatistics findShortenedLinkStatistics(Long id){
        logger.info("Retrieving Statistic with ID {}", id);
        return statisticsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Statistics with id " + id + " not found"));
    }
}
