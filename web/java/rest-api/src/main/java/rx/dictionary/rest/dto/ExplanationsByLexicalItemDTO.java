package rx.dictionary.rest.dto;

import io.github.rxue.dictionary.jpa.entity.Explanation;

import java.util.*;

public final class ExplanationsByLexicalItemDTO {
    private final LexicalItemDTO lexicalItemDTO;
    private final List<ExplanationDTO> explanationDTOs;

    private ExplanationsByLexicalItemDTO(LexicalItemDTO lexicalItemDTO, List<ExplanationDTO> explanationDTOs) {
        this.lexicalItemDTO = lexicalItemDTO;
        this.explanationDTOs = explanationDTOs;
    }

    public LexicalItemDTO getLexicalItemDTO() {
        return lexicalItemDTO;
    }
    public List<ExplanationDTO> getExplanations() {
        return explanationDTOs;
    }
    public static ExplanationsByLexicalItemDTO create(List<Explanation> explanations) {
        List<ExplanationDTO> explanationDTOs = explanations.stream()
                .map(e -> new ExplanationDTO.Builder()
                        .setId(e.getId())
                        .setExplanationLanguage(e.getLanguage().toLanguageTag())
                        .setPartOfSpeech(e.getPartOfSpeech().toString())
                        .setDefinition(e.getDefinition())
                        .build()
                ).toList();
        return new ExplanationsByLexicalItemDTO(LexicalItemDTO.create(explanations.get(0).getLexicalItem()), explanationDTOs);
    }
}
