package rx.dictionary.rest.dto;

public final class ExplanationDTO {
    private final Long id;
    private final String explanationLanguage;
    private final String partOfSpeech;
    private final String definition;
    private ExplanationDTO(Builder builder) {
        this.id = builder.id;
        this.explanationLanguage = builder.explanationLanguage;
        this.partOfSpeech = builder.partOfSpeech;
        this.definition = builder.definition;
    }

    public Long getId() {
        return id;
    }


    public String getExplanationLanguage() {
        return explanationLanguage;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }
    static class Builder {
        private Long id;
        private String explanationLanguage;
        private String partOfSpeech;
        private String definition;
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        Builder setExplanationLanguage(String explanationLanguage) {
            this.explanationLanguage = explanationLanguage;
            return this;
        }

        Builder setPartOfSpeech(String partOfSpeech) {
            this.partOfSpeech = partOfSpeech;
            return this;
        }

        Builder setDefinition(String definition) {
            this.definition = definition;
            return this;
        }
        ExplanationDTO build() {
            return new ExplanationDTO(this);
        }
    }
}
