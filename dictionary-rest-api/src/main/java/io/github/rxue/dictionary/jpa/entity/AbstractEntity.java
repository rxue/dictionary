package io.github.rxue.dictionary.jpa.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private TimestampAttributes timestampAttributes;

    public Long getId() {
        return id;
    }

    public TimestampAttributes getTimestampAttributes() {
        return timestampAttributes;
    }

    public void setTimestampAttributes(TimestampAttributes timestampAttributes) {
        this.timestampAttributes = timestampAttributes;
    }
}
