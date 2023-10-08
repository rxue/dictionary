package rx.dictionary.dto;

public class ExplanationDTO {
    private LexicalItemDTO lexicalItem;
    private String partOfSpeech;
    private String explanationLanguage;
    private String explanation;

    public void setLexicalItem(LexicalItemDTO lexicalItem) {
        this.lexicalItem = lexicalItem;
    }

    public LexicalItemDTO getLexicalItem() {
        return lexicalItem;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setExplanationLanguage(String explanationLanguage) {
        this.explanationLanguage = explanationLanguage;
    }

    public String getExplanationLanguage() {
        return explanationLanguage;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }
}
