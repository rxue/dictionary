package rx.dictionary.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import rx.dictionary.vo.Explanation;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@EqualsAndHashCode
@Table(name = "explanation", uniqueConstraints = { @UniqueConstraint(columnNames = { "lexical_item_id", "language", "partofspeech", "definition"}) })
@SequenceGenerator(sequenceName = "explanation_id_seq", initialValue=1, name = "explanation_sequence", allocationSize = 4)
@Entity
public class ExplanationEntity implements Explanation {

    @Id
    @GeneratedValue(generator="explanation_sequence", strategy=GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="lexical_item_id")
    private LexicalItemEntity lexicalItemEntity;
    @Column(nullable = false)
    private Locale language;
    @Enumerated(EnumType.STRING)
    private PartOfSpeech partOfSpeech;
    @Column(nullable = false)
    private String definition;
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "sentence")
    private Set<String> sentences = new HashSet<>();
    @Embedded
    private DateAttributes dateAttributes;

    public ExplanationEntity(Long id, LexicalItemEntity lexicalItemEntity) {
        this.id = id;
        this.lexicalItemEntity = lexicalItemEntity;
    }
    public ExplanationEntity() {}

    public LexicalItemEntity getLexicalItemEntity() {
        return lexicalItemEntity;
    }
    @Override
    public Long getId() {
        return id;
    }

    public String getLanguage() {
        return language.toString();
    }
    public void setLanguage(Locale language) {
        this.language = language;
    }
    @Override
    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }
    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
    @Override
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

