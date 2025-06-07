package io.github.rxue.dictionary.jpa.entity;

import io.github.rxue.dictionary.Keyword;
import jakarta.persistence.*;

import java.util.Locale;

@Entity
@Table(name = "lexical_item")
@SequenceGenerator(sequenceName = "lexical_item_seq", initialValue=1, name = "lexical_item_id_sequence", allocationSize = 1)
public class LexicalItem implements Keyword {

    @Id
    @GeneratedValue(generator="lexical_item_id_sequence", strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable=false)
    private Locale language;
    @Column(nullable=false)
    private String value;
    private TimestampAttributes dateAttributes;
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

    public TimestampAttributes getDateAttributes() {
        return dateAttributes;
    }

    public void setDateAttributes(TimestampAttributes dateAttributes) {
        this.dateAttributes = dateAttributes;
    }
}
