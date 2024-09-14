package rx.dictionary.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import rx.dictionary.rest.vo.ExplanationVO;

import java.util.List;

public class NewExplanationsByLexicalItemInputDTO {
    @NotNull
    private LexicalItemDTO lexicalItemDTO;
    @NotEmpty
    private List<ExplanationVO> explanations;

    public LexicalItemDTO getLexicalItemDTO() {
        return lexicalItemDTO;
    }

    public void setLexicalItemDTO(LexicalItemDTO lexicalItemDTO) {
        this.lexicalItemDTO = lexicalItemDTO;
    }

    public List<ExplanationVO> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<ExplanationVO> explanations) {
        this.explanations = explanations;
    }
}
