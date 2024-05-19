package rx.dictionary.rest.dto;

import rx.dictionary.jpa.entity.Explanation;

import java.util.*;

public class ExplanationsByLanguageDTO {
    private final String language;
    private final Map<String, Set<String>> explanationsByPartOfSpeech;
    private ExplanationsByLanguageDTO(Builder builder) {
        this.language = builder.language;
        this.explanationsByPartOfSpeech = builder.explanationsByPartOfSpeech;
    }

    public String getLanguage() {
        return language;
    }

    public Map<String, Set<String>> getExplanationsByPartOfSpeech() {
        return explanationsByPartOfSpeech;
    }

    public static class Builder {
        private final String language;
        private Map<String, Set<String>> explanationsByPartOfSpeech;
        public Builder(Locale language) {
            this.language = language.getDisplayLanguage();
            this.explanationsByPartOfSpeech = new HashMap<>();
        }
        public Builder addExplanation(Explanation explanation) {
            final String partOfSpeech = explanation.getPartOfSpeech().toString();
            Set<String> existingExplanations = explanationsByPartOfSpeech.get(partOfSpeech);
            if (existingExplanations != null) {
                Set<String> overriddenExplanations = new HashSet<>();
                overriddenExplanations.addAll(existingExplanations);
                overriddenExplanations.add(explanation.getExplanation());
                explanationsByPartOfSpeech.put(partOfSpeech, overriddenExplanations);
            } else explanationsByPartOfSpeech.put(partOfSpeech, Set.of(explanation.getExplanation()));
            return this;
        }
        public ExplanationsByLanguageDTO build() {
            return new ExplanationsByLanguageDTO(this);
        }
    }
}
