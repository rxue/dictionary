package rx.dictionary.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@EqualsAndHashCode
@Table(name = "explanation", uniqueConstraints = { @UniqueConstraint(columnNames = {"lexical_item_id", "language", "partofspeech", "explanation"}) })
@SequenceGenerator(sequenceName = "explanation_id_seq", initialValue=1, name = "explanation_sequence", allocationSize = 4)
@Entity
public class Explanation {

    @Id
    @GeneratedValue(generator="explanation_sequence", strategy=GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne // Defaults to EAGER
    @JoinColumn(name="lexical_item_id")
    private LexicalItem lexicalItem;
    @Column(nullable = false)
    private Locale language;
    @Enumerated(EnumType.STRING)
    private PartOfSpeech partOfSpeech;
    @Column(nullable = false)
    private String explanation;
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "sentence")
    private Set<String> sentences = new HashSet<>();

    private DateAttributes dateAttributes;

    public Explanation(Long id) {
        this.id = id;
    }
    public Explanation() {
    }

    public Long getId() {
        return id;
    }

    public LexicalItem getLexicalItem() {
        return lexicalItem;
    }

    public void setLexicalItem(LexicalItem lexicalItem) {
        this.lexicalItem = lexicalItem;
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

    public Set<String> getSentences() {
        return sentences;
    }

    public void setSentences(Set<String> sentences) {
        this.sentences = sentences;
    }
}

