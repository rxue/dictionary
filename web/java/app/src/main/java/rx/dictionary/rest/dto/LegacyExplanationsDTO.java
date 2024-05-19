package rx.dictionary.rest.dto;

import java.util.List;

public class LegacyExplanationsDTO {
    private List<LegacyExplanationDTO> explanations;

    public List<LegacyExplanationDTO> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<LegacyExplanationDTO> explanations) {
        this.explanations = explanations;
    }
}
