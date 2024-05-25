package rx.dictionary.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Embedded
    private DateAttributes dateAttributes;
}
