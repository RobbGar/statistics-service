package com.robb.statisticsservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robb.statisticsservice.model.ShortenedLinkStatistics;
import com.robb.statisticsservice.services.StatisticsService;

import jakarta.persistence.EntityNotFoundException;

@RestController()
@RequestMapping("/statistics")
public class StatisticsController {
    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatisticsOfLink(@PathVariable Long id) {
        try {
            final ShortenedLinkStatistics shortenedLinkStatistics = statisticsService.findShortenedLinkStatistics(id);
            return new ResponseEntity<Integer>(shortenedLinkStatistics.getHits(), HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            logger.info("Not FOUND");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
