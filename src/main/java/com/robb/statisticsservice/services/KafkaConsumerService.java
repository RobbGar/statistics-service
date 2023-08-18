package com.robb.statisticsservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Service
public class KafkaConsumerService {
    
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final StatisticsService statisticsService;

    @Autowired
    public KafkaConsumerService(StatisticsService statisticsService){
        this.statisticsService = statisticsService;
    }

    @KafkaListener(topics = "statistics", groupId = "statistics-consumer")
    public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        logger.info("Received message from kafka: {}", message);
        statisticsService.updateOrCreateStatistics(Long.parseLong(key));
    }
}
