package rx.dictionary.rest.dto;

public class LexicalItemWithExplanationsDTO extends ExplanationsDTO {
    private LexicalItemDTO lexicalItem;
    private String explanationLanguage;

    public LexicalItemDTO getLexicalItem() {
        return lexicalItem;
    }

    public void setLexicalItem(LexicalItemDTO lexicalItem) {
        this.lexicalItem = lexicalItem;
    }

    public String getExplanationLanguage() {
        return explanationLanguage;
    }

    public void setExplanationLanguage(String explanationLanguage) {
        this.explanationLanguage = explanationLanguage;
    }
}
