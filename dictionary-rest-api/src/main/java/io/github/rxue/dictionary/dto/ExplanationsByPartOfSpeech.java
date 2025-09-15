package io.github.rxue.dictionary.dto;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;
import lombok.Value;

import java.util.List;

@Value
public class ExplanationsByPartOfSpeech {
    private String partOfSpeech;
    private List<String> explanations;
    private ExplanationsByPartOfSpeech(String partOfSpeech, List<String> explanations) {
        this.partOfSpeech = partOfSpeech;
        this.explanations = explanations;
    }

    public static ExplanationsByPartOfSpeech toExpalantionsByPartOfSpeech(List<Explanation> explanationsWithTheSamePartOfSpeech) {
        List<String> explanationValues = explanationsWithTheSamePartOfSpeech.stream()
                .map(Explanation::getExplanation)
                .toList();
        PartOfSpeech partOfSpeech = explanationsWithTheSamePartOfSpeech.stream()
                .findAny().map(Explanation::getPartOfSpeech)
                .orElseThrow(IllegalArgumentException::new);
        return new ExplanationsByPartOfSpeech(partOfSpeech.name(), explanationValues);
    }
}
