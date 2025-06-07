package io.github.rxue.dictionary;

import io.github.rxue.dictionary.dto.ExplanationsByLanguageDTO;
import io.github.rxue.dictionary.dto.ExplanationsByPartOfSpeech;
import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

@ApplicationScoped
public class DictionaryService {

    private EntityManager entityManager;
    public DictionaryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    List<LexicalItem> getAllLexicalItems() {
        return entityManager.createQuery("SELECT l FROM LexicalItem l")
                .getResultList();
    }

    Optional<ExplanationsByLanguageDTO> getExplanationsByLanguage(Locale language, String word, Locale explanationLanguage) {
        Map<PartOfSpeech, List<Explanation>> partOfSpeechToExplanations = entityManager.createQuery("SELECT e FROM LexicalItem l LEFT JOIN Explanation e ON l = e.lexicalItem WHERE l.value = :word AND l.language = :language and e.language =:explanationLanguage", Explanation.class)
                .setParameter("word", word)
                .setParameter("language", language)
                .setParameter("explanationLanguage", explanationLanguage)
                .getResultStream()
                .collect(groupingBy(Explanation::getPartOfSpeech));
        if (partOfSpeechToExplanations.isEmpty())
            return Optional.empty();
        else return Optional.of(ExplanationsByLanguageDTO.builder()
                .language(language.toLanguageTag())
                .lexicalItem(word)
                .explanationInLanguage(explanationLanguage.toLanguageTag())
                .explanations(toExplanationsByPartOfSpeechList(partOfSpeechToExplanations))
                .build());

    }
    private List<ExplanationsByPartOfSpeech> toExplanationsByPartOfSpeechList(Map<PartOfSpeech, List<Explanation>> partOfSpeechToExplanations) {
        return partOfSpeechToExplanations.keySet().stream()
                .map(partOfSpeech -> ExplanationsByPartOfSpeech.toExpalantionsByPartOfSpeech(partOfSpeechToExplanations.get(partOfSpeech)))
                .toList();
    }

}
