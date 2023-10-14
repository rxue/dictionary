package rx.dictionary.rest.dto;

import java.util.List;

public class ExplanationsDTO {
    private LexicalItemDTO lexicalItem;
    private String explanationLanguage;
    private List<ExplanationDTO> explanations;

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

    public List<ExplanationDTO> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<ExplanationDTO> explanations) {
        this.explanations = explanations;
    }
}
