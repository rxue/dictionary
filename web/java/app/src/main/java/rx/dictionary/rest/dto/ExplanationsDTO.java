package rx.dictionary.rest.dto;

import java.util.List;

public class ExplanationsDTO {
    private List<ExplanationDTO> explanations;

    public List<ExplanationDTO> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<ExplanationDTO> explanations) {
        this.explanations = explanations;
    }
}
