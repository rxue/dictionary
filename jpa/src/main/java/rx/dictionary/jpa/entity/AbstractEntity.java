package rx.dictionary.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(name="creation_date")
    private LocalDate creationDate;
    @Column(name="last_update_time")
    private ZonedDateTime lastUpdateTime;

    @Column(name="creation_date")
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Column(name="last_update_time")
    public ZonedDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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
