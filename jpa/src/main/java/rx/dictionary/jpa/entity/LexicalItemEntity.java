package rx.dictionary.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import rx.dictionary.vo.LexicalItem;

import java.util.Locale;

@EqualsAndHashCode
@Entity
@Table(name = "lexical_item",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"language", "value"})
})
public class LexicalItemEntity extends AbstractEntity implements LexicalItem {
    @Column(nullable=false)
    private Locale language;
    @Column(nullable=false)
    private String value;
    //Merely for testing purpose
    public LexicalItemEntity(Long id) {
        this.id = id;
    }
    public LexicalItemEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getLanguage() {
        return language.toString();
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
