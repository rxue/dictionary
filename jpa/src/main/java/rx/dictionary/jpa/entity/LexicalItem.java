package rx.dictionary.jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Locale;
import java.util.Set;

@EqualsAndHashCode
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
    @OneToMany
    @JoinColumn(name="lexical_item_id")
    private Set<Explanation> explanations;

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

    public Set<Explanation> getExplanations() {
        return explanations;
    }

    public void setExplanations(Set<Explanation> explanations) {
        this.explanations = explanations;
    }
}
