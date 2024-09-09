package rx.dictionary.rest.dto;

import io.github.rxue.dictionary.jpa.entity.LexicalItem;

import java.util.*;

public final class ExplanationsByLexicalItemDTO {
    private final Long lexicalItemId;
    private final String language;
    private final String value;
    private final List<ExplanationDTO> explanations;
    public ExplanationsByLexicalItemDTO(LexicalItem lexicalItem, List<ExplanationDTO> explanations) {
        this.lexicalItemId = lexicalItem.getId();
        this.language = lexicalItem.getLanguage().toLanguageTag();
        this.value = lexicalItem.getValue();
        this.explanations = explanations;
    }

    public Long getLexicalItemId() {
        return lexicalItemId;
    }
    public String getLanguage() {
        return language;
    }
    public String getValue() {
        return value;
    }
    public List<ExplanationDTO> getExplanations() {
        return explanations;
    }
}
