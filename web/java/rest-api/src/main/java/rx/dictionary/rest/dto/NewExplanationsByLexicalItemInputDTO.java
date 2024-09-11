package rx.dictionary.rest.dto;



import jakarta.validation.constraints.NotNull;
import rx.dictionary.rest.vo.ExplanationVO;

import java.util.List;

public class NewExplanationsByLexicalItemInputDTO {
    private Long lexicalItemId;
    @NotNull
    private String language;
    @NotNull
    private String value;
    private List<ExplanationVO> explanations;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ExplanationVO> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<ExplanationVO> explanations) {
        this.explanations = explanations;
    }
}
