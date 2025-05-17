package io.github.rxue.dictionary.jpa.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Embedded
    private DateAttributes dateAttributes;
    public Long getId() {
        return id;
    }
}
