package rx.dictionary.dto;

import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.vo.LexicalItemVO;

import java.util.Locale;

public final class ExplanationDTO {
    private final LexicalItemVO lexicalItemVO;
    private final PartOfSpeech partOfSpeech;
    private final Locale explanationLanguage;
    private final String explanation;

    private ExplanationDTO(Builder builder) {
        this.lexicalItemVO = builder.lexicalItemVO;
        this.partOfSpeech = builder.partOfSpeech;
        this.explanationLanguage = builder.explanationLanguage;
        this.explanation = builder.explanation;
    }

    public LexicalItemVO getLexicalItemVO() {
        return lexicalItemVO;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public Locale getExplanationLanguage() {
        return explanationLanguage;
    }

    public String getExplanation() {
        return explanation;
    }
    public static class Builder {
        private final LexicalItemVO lexicalItemVO;
        private PartOfSpeech partOfSpeech;
        private Locale explanationLanguage;
        private String explanation;

        public Builder(LexicalItemVO lexicalItemVO) {
            this.lexicalItemVO = lexicalItemVO;
        }

        public Builder setPartOfSpeech(PartOfSpeech partOfSpeech) {
            this.partOfSpeech = partOfSpeech;
            return this;
        }

        public Builder setExplanationLanguage(Locale explanationLanguage) {
            this.explanationLanguage = explanationLanguage;
            return this;
        }

        public Builder setExplanation(String explanation) {
            this.explanation = explanation;
            return this;
        }
        public ExplanationDTO build() {
            return new ExplanationDTO(this);
        }
    }
}
