package rx.dictionary.rest.dto;

public class LexicalItemWithLegacyExplanationsDTO extends LegacyExplanationsDTO {
    private LegacyLexicalItemDTO lexicalItem;
    private String explanationLanguage;

    public LegacyLexicalItemDTO getLexicalItem() {
        return lexicalItem;
    }

    public void setLexicalItem(LegacyLexicalItemDTO lexicalItem) {
        this.lexicalItem = lexicalItem;
    }

    public String getExplanationLanguage() {
        return explanationLanguage;
    }

    public void setExplanationLanguage(String explanationLanguage) {
        this.explanationLanguage = explanationLanguage;
    }
}
