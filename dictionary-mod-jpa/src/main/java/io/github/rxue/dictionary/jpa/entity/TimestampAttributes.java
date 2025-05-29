package io.github.rxue.dictionary.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Embeddable
public class TimestampAttributes {
    @Column(name="creation_date")
    public LocalDate creationDate;
    @Column(name="last_update_time")
    public ZonedDateTime lastUpdateTime;

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(ZonedDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @PrePersist
    public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }
    @PreUpdate
    public void setLastUpdateTime() {
        this.lastUpdateTime = ZonedDateTime.now();
    }
}
