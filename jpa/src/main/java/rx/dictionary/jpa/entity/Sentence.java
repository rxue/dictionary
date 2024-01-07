package rx.dictionary.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Set;

//@Entity
@Embeddable
public class Sentence {
    @Column(nullable = false)
    private final String sentence;

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }

/*    public void setSentence(String sentence) {
        this.sentence = sentence;
    }*/
}
