package io.github.rxue.dictionary.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Locale;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"sentence"}))
public class Sentence extends AbstractEntity {
    private Locale language;
    @Column(nullable=false)
    private String sentence;
    private String resource;

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
