package io.github.rxue.dictionary.jpa.entity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@EqualsAndHashCode
@Table(name = "Explanation", uniqueConstraints = { @UniqueConstraint(columnNames = { "explanation"}) })
@Entity
public class Explanation extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="lexical_item_id")
    private LexicalItem lexicalItem;
    @Column(nullable = false)
    private Locale language;
    @Enumerated(EnumType.STRING)
    private PartOfSpeech partOfSpeech;
    @Column(nullable = false)
    private String explanation;
    @ManyToMany
    private Set<Sentence> sentences = new HashSet<>();
    @Embedded
    private TimestampAttributes timeStampAttributes;

    public Explanation(Long id, LexicalItem lexicalItem) {
        this.id = id;
        this.lexicalItem = lexicalItem;
    }
    /**
     * Construct a new Explanation with a given LexicalItem
     *
     * @param lexicalItem
     */
    public Explanation(LexicalItem lexicalItem) {
        this(null, lexicalItem);
    }
    public Explanation() {}

    public LexicalItem getLexicalItem() {
        return lexicalItem;
    }

    public Locale getLanguage() {
        return language;
    }
    public void setLanguage(Locale language) {
        this.language = language;
    }
    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }
    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Set<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(Set<Sentence> sentences) {
        this.sentences = sentences;
    }
}
