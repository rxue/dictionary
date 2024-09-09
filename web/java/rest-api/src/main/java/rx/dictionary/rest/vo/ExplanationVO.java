package rx.dictionary.rest.vo;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;

import java.util.Locale;

public class ExplanationVO {
    private String explanationLanguageTag;
    private String partOfSpeech;
    private String definition;

    public String getExplanationLanguageTag() {
        return explanationLanguageTag;
    }

    public void setExplanationLanguageTag(String explanationLanguageTag) {
        this.explanationLanguageTag = explanationLanguageTag;
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
        explanation.setLanguage(Locale.forLanguageTag(explanationLanguageTag));
        explanation.setPartOfSpeech(PartOfSpeech.valueOf(PartOfSpeech.class,partOfSpeech));
        explanation.setDefinition(definition);
        return explanation;
    }
}
