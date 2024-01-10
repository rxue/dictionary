package rx.dictionary.rest.dto;

import java.util.Set;

public class ExplanationDTO {
    private String partOfSpeech;
    private String explanation;
    private Set<String> sentences;

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }


    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    public Set<String> getSentences() {
        return sentences;
    }

    public void setSentences(Set<String> sentences) {
        this.sentences = sentences;
    }
}
