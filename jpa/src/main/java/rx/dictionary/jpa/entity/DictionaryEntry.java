package rx.dictionary.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Locale;

@EqualsAndHashCode
@Entity
@Table(name = "lexical_item",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"language", "value"})
})
public class DictionaryEntry extends AbstractEntity {
    @Column(nullable=false)
    private Locale language;
    @Column(nullable=false)
    private String value;
    //Merely for testing purpose
    public DictionaryEntry(Long id) {
        this.id = id;
    }
    public DictionaryEntry() {
    }

    public Long getId() {
        return id;
    }

    public Locale getLanguage() {
        return language;
    }

    public String getValue() {
        return value;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
