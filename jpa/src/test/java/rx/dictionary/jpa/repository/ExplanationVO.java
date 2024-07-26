package rx.dictionary.jpa.repository;

import rx.dictionary.jpa.entity.PartOfSpeech;

record ExplanationVO(LexicalItemVO lexicalItemVO, String language, PartOfSpeech partOfSpeech, String definition) {
}
