package io.github.rxue.dictionary.jpa.entity;

import jakarta.persistence.*;

import java.util.Locale;
@Entity
public class Context extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="sentence_id")
    private Sentence sentence;
    private Locale language;
    private String description;

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
