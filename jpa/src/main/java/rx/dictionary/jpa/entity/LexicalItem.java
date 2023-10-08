package rx.dictionary.jpa.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "lexical_item",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"language", "value"})
})
public class LexicalItem extends AbstractEntity {
    @Column(nullable=false)
    private Locale language;
    @Column(nullable=false)
    private String value;

    public long getId() {
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
}
