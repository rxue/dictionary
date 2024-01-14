package rx.dictionary.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Embeddable
public class DateAttributes {
    @Column(name="creation_date")
    private LocalDate creationDate;
    @Column(name="last_update_time")
    private ZonedDateTime lastUpdateTime;

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
}
