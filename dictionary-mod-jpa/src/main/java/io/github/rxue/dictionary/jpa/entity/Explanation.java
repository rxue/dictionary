package io.github.rxue.dictionary.jpa.entity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@EqualsAndHashCode
@Table(name = "explanation", uniqueConstraints = { @UniqueConstraint(columnNames = { "lexical_item_id", "language", "partofspeech", "serialNumber"}) })
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
    private Integer serialNumber;
    @Column(nullable = false)
    private String definition;
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "sentence")
    private Set<String> sentences = new HashSet<>();
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

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDefinition() {
        return definition;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Set<String> getSentences() {
        return sentences;
    }

    public void setSentences(Set<String> sentences) {
        this.sentences = sentences;
    }
}
