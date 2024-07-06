package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.DictionaryEntry;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ExplanationRepository {
    private final EntityManager entityManager;
    public ExplanationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Explanation> findLike(Keyword keyword, Locale definitionLanguage) {
        String jpql = "select e from Explanation e where " +
                "e.dictionaryEntry.language =: language and " +
                "e.dictionaryEntry.value like :value and " +
                "e.language =: definitionLanguage";
        return entityManager.createQuery(jpql, Explanation.class)
                .setParameter("language", keyword.language())
                .setParameter("value", "test%")
                .setParameter("definitionLanguage", definitionLanguage)
                .getResultList();
    }
}
