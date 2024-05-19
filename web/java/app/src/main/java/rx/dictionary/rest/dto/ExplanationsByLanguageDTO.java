package rx.dictionary.rest.dto;

import rx.dictionary.jpa.entity.Explanation;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ExplanationsByLanguageDTO {
    private final String language;
    private final Map<String, List<String>> explanationsByPartOfSpeech;
    private ExplanationsByLanguageDTO(Builder builder) {
        this.language = builder.language;
        this.explanationsByPartOfSpeech = builder.explanationsByPartOfSpeech;
    }

    public String getLanguage() {
        return language;
    }

    public Map<String, List<String>> getExplanationsByPartOfSpeech() {
        return explanationsByPartOfSpeech;
    }

    public static class Builder {
        private final String language;
        private Map<String, List<String>> explanationsByPartOfSpeech;
        public Builder(Locale language) {
            this.language = language.getDisplayLanguage();
            this.explanationsByPartOfSpeech = new HashMap<>();
        }
        public Builder addExplanation(Explanation explanation) {
            explanationsByPartOfSpeech.put(explanation.getPartOfSpeech().toString(), List.of("test"));
            return this;
        }
        public ExplanationsByLanguageDTO build() {
            return new ExplanationsByLanguageDTO(this);
        }
    }
}
