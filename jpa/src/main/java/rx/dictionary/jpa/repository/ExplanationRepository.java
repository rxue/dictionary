package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ExplanationRepository {
    private final EntityManager entityManager;
    public ExplanationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Explanation> find(Keyword keyword, Locale explanationLanguage) {
        return entityManager.createQuery("select e from Explanation e where e.language =: explanationLanguage" +
                        " and e.lexicalItem.language =: language and e.lexicalItem.value =: value", Explanation.class)
                .setParameter("explanationLanguage", explanationLanguage)
                .setParameter("language", keyword.language())
                .setParameter("value", keyword.value())
                .getResultList();
    }
}
