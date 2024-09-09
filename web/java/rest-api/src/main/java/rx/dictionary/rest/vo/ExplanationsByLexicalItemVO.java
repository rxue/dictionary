package rx.dictionary.rest.vo;

import java.util.List;

public class ExplanationsByLexicalItemVO {
    private String lexicalItemLanguage;
    private String lexicalItemValue;
    private List<ExplanationVO> explanations;

    public String getLexicalItemLanguage() {
        return lexicalItemLanguage;
    }

    public void setLexicalItemLanguage(String lexicalItemLanguage) {
        this.lexicalItemLanguage = lexicalItemLanguage;
    }

    public String getLexicalItemValue() {
        return lexicalItemValue;
    }

    public void setLexicalItemValue(String lexicalItemValue) {
        this.lexicalItemValue = lexicalItemValue;
    }

    public List<ExplanationVO> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<ExplanationVO> explanations) {
        this.explanations = explanations;
    }
}
