package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.List;
import java.util.Locale;

public class ExplanationRepositoryImpl {
    private final EntityManager entityManager;
    public ExplanationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Explanation> findLike(Keyword keyword, Locale explanationLanguage) {
        return entityManager.createQuery("from Explanation e where e.language =: explanationLanguage" +
                        " and e.lexicalItem.language =: language and e.lexicalItem.value like :value", Explanation.class)
                .setParameter("explanationLanguage", explanationLanguage)
                .setParameter("language", keyword.language())
                .setParameter("value", keyword.value())
                .getResultList();
    }

    public void create(List<Explanation> explanation) {
        entityManager.persist(explanation);
    }
}
