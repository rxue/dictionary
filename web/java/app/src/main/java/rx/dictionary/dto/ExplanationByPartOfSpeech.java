package rx.dictionary.dto;

import rx.dictionary.jpa.entity.PartOfSpeech;

import java.util.List;

public class ExplanationByPartOfSpeech {
    protected final PartOfSpeech partOfSpeech;
    protected final List<String> explanations;

    public ExplanationByPartOfSpeech(PartOfSpeech partOfSpeech, List<String> explanations) {
        this.partOfSpeech = partOfSpeech;
        this.explanations = explanations;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public List<String> getExplanations() {
        return explanations;
    }
}
