package rx.dictionary.vo;

import rx.dictionary.jpa.entity.PartOfSpeech;

public interface Explanation {
    Long getId();
    PartOfSpeech getPartOfSpeech();
    String getDefinition();
}
