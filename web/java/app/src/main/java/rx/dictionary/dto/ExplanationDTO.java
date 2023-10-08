package rx.dictionary.dto;

import rx.dictionary.jpa.entity.LexicalItem;

import java.util.Locale;

public class ExplanationDTO {
    private LexicalItemDTO lexicalItem;
    private String partOfSpeech;
    private Locale explanationLanguage;
    private String explanation;

    public LexicalItemDTO getLexicalItem() {
        return lexicalItem;
    }

    public void setLexicalItem(LexicalItemDTO lexicalItem) {
        this.lexicalItem = lexicalItem;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public Locale getExplanationLanguage() {
        return explanationLanguage;
    }

    public String getExplanation() {
        return explanation;
    }
}
