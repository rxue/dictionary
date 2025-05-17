package io.github.rxue.dictionary.jpa.entity;

import io.github.rxue.dictionary.Keyword;
import jakarta.persistence.*;

import java.util.Locale;

@Entity
@Table(name = "lexical_item")
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
    @Embedded
    private DateAttributes dateAttributes;

    @Override
    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    @Override
    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }
}
