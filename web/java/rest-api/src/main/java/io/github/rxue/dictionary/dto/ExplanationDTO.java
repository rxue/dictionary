package io.github.rxue.dictionary.dto;

import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;

public interface ExplanationDTO {
    Long getId();
    PartOfSpeech getPartOfSpeech();
    String getDefinition();
}
