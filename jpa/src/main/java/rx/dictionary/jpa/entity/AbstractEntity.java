package rx.dictionary.jpa.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    protected long id;
    private LocalDate creationDate;

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
