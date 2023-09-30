package rx.dictionary.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Locale;

@Entity
@Table(name = "lexical_item")
public class LexicalItem extends AbstractEntity {
    private Locale language;
    private String value;
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
