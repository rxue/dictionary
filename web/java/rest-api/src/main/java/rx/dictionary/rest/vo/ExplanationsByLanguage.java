package rx.dictionary.rest.vo;

import java.util.*;
import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;

public final class ExplanationsByLanguage {
    private final Long lexicalItemId;
    private final String language;
    private final String value;
    private final String explanationLanguage;
    private final Map<PartOfSpeech, List<ExplanationVO>> definitions;
    private ExplanationsByLanguage(Builder builder) {
        this.lexicalItemId = builder.lexicalItemId;
        this.language = builder.language;
        this.value = builder.value;
        this.explanationLanguage = builder.explanationLanguage;
        this.definitions = Collections.unmodifiableMap(builder.definitions);
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

    public String getExplanationLanguage() {
        return explanationLanguage;
    }

    public Map<PartOfSpeech, List<ExplanationVO>> getDefinitions() {
        return definitions;
    }

    public static class Builder {
        private final Long lexicalItemId;
        private final String language;
        private final String value;
        private String explanationLanguage;
        private Map<PartOfSpeech,List<ExplanationVO>> definitions;
        public Builder(Long lexicalItemId, Locale language, String value) {
            this.lexicalItemId = lexicalItemId;
            this.language = toLanguageName(language);
            this.value = value;
            definitions = new HashMap<>();
        }
        public Builder setExplanationLanguage(Locale explanationLanguage) {
            this.explanationLanguage = toLanguageName(explanationLanguage);
            return this;
        }
        public Builder addDefinition(PartOfSpeech partOfSpeech, Long explanationId, String definition) {
            List<ExplanationVO> existingDefinitionsByPartOfSpeech = definitions.get(partOfSpeech);
            if (existingDefinitionsByPartOfSpeech != null) {
                existingDefinitionsByPartOfSpeech.add(new ExplanationVO(explanationId, definition));
            }
            else {
                definitions.put(partOfSpeech, List.of(new ExplanationVO(explanationId, definition)));
            }
            return this;
        }
        public ExplanationsByLanguage build() {
            return new ExplanationsByLanguage(this);
        }
        private static String toLanguageName(Locale languageLocale) {
            return languageLocale.toLanguageTag();
        }
    }
}
