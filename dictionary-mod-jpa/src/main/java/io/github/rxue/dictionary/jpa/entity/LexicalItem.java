package io.github.rxue.dictionary.jpa.entity;

import io.github.rxue.dictionary.Keyword;
import jakarta.persistence.*;

import java.util.Locale;

@Entity
@Table(name = "lexical_item",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"language", "value"})
})
public class LexicalItem extends AbstractEntity implements Keyword {
    @Column(nullable=false)
    private Locale language;
    @Column(nullable=false)
    private String value;
    //Merely for testing purpose
    public LexicalItem(Long id) {
        this.id = id;
    }
    public LexicalItem() {
    }
    public Long getId() {
        return id;
    }

    @Override
    public Locale getLanguage() {
        return language;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
