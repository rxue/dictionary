package rx.dictionary.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@EqualsAndHashCode
//@Table(name = "explanation", uniqueConstraints = { @UniqueConstraint(columnNames = { "item_id", "language", "partofspeech", "explanation"}) })
//@SequenceGenerator(sequenceName = "explanation_id_seq", initialValue=1, name = "explanation_sequence", allocationSize = 4)
@Entity
public class Explanation extends AbstractEntity {
    private Locale language;
    private PartOfSpeech partOfSpeech;
    private String explanation;

    private Set<String> sentences = new HashSet<>();

    @Id
    @GeneratedValue(generator="explanation_sequence", strategy=GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }
    @Column(nullable = false)
    public Locale getLanguage() {
        return language;
    }
    public void setLanguage(Locale language) {
        this.language = language;
    }
    @Enumerated(EnumType.STRING)
    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }
    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
    @Column(nullable = false)
    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "sentence")
    public Set<String> getSentences() {
        return sentences;
    }

    public void setSentences(Set<String> sentences) {
        this.sentences = sentences;
    }
}

