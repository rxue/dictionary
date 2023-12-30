package rx.dictionary.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Locale;

@EqualsAndHashCode
@Table(name = "explanation", uniqueConstraints = { @UniqueConstraint(columnNames = { "item_id", "language", "partofspeech", "explanation"}) })
@SequenceGenerator(sequenceName = "explanation_id_seq", initialValue=1, name = "explanation_sequence", allocationSize = 4)
@Entity
public class Explanation extends AbstractEntity {
    private LexicalItem lexicalItem;
    private Locale language;
    private PartOfSpeech partOfSpeech;
    private String explanation;

    @Id
    @GeneratedValue(generator="explanation_sequence", strategy=GenerationType.SEQUENCE)
    public long getId() {
        return id;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="item_id", nullable=false)
    public LexicalItem getLexicalItem() {
        return lexicalItem;
    }
    public void setLexicalItem(LexicalItem lexicalItem) {
        this.lexicalItem = lexicalItem;
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

}

