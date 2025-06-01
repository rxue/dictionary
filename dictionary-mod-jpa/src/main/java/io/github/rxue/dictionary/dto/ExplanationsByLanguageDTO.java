package io.github.rxue.dictionary.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ExplanationsByLanguageDTO {
    private String language;
    private String lexicalItem;
    private String explanationInLanguage;
    private List<ExplanationsByPartOfSpeech> explanations;

}
