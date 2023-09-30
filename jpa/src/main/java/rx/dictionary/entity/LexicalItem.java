package rx.dictionary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lexical_item")
public class LexicalItem {
    @Id
    private long id;
    private String value;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
