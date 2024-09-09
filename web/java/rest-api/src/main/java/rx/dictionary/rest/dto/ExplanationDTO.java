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
    public static class Builder {
        private Long id;
        private String explanationLanguage;
        private String partOfSpeech;
        private String definition;
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setExplanationLanguage(String explanationLanguage) {
            this.explanationLanguage = explanationLanguage;
            return this;
        }

        public Builder setPartOfSpeech(String partOfSpeech) {
            this.partOfSpeech = partOfSpeech;
            return this;
        }

        public Builder setDefinition(String definition) {
            this.definition = definition;
            return this;
        }
        public ExplanationDTO build() {
            return new ExplanationDTO(this);
        }
    }
}
