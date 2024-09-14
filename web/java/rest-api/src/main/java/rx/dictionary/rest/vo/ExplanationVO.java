package rx.dictionary.rest.vo;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;

public class ExplanationVO {
    @NotNull
    private String languageTag;
    private String partOfSpeech;
    @NotNull
    private String definition;

    public String getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Explanation toExplanation(LexicalItem lexicalItem) {
        Explanation explanation = new Explanation(lexicalItem);
        explanation.setLanguage(Locale.forLanguageTag(languageTag));
        explanation.setPartOfSpeech(PartOfSpeech.valueOf(PartOfSpeech.class,partOfSpeech));
        explanation.setDefinition(definition);
        return explanation;
    }
}
