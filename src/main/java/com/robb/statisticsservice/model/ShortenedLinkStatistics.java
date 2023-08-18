package com.robb.statisticsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ShortenedLinkStatistics {

    @Id
    private Long id;

    @Column(name = "hits", nullable = false)
    private int hits;

    public ShortenedLinkStatistics() {
    }

    public int getHits() {
        return hits;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void increaseHits() {
        this.hits++;
    }
}
