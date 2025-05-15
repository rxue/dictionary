package io.github.rxue.dictionary.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestEntity {
    @Id
    private Integer id;
    public Integer getId() {
        return id;
    }
}
