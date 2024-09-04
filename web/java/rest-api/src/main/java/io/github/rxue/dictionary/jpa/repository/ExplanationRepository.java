package io.github.rxue.dictionary.jpa.repository;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.vo.Keyword;
import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ExplanationRepository {
    private final EntityManager entityManager;
    public ExplanationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Finds possible explanations on base of the given keyword and language
     *
     * @param keyword keyword
     * @param definitionLanguage language for the explanation
     * @return list of explanations
     */
    public List<Explanation> findLike(Keyword keyword, Locale definitionLanguage) {
        String jpql = "select e from Explanation e where " +
                "e.lexicalItem.language =: language and " +
                "e.lexicalItem.value like :value and " +
                "e.language =: definitionLanguage";
        return entityManager.createQuery(jpql, Explanation.class)
                .setParameter("language", keyword.getLanguage())
                .setParameter("value", keyword.getValue() + "%")
                .setParameter("definitionLanguage", definitionLanguage)
                .getResultList();
    }

    /**
     * Updates the given collection of explanations in cascade
     *
     * @param explanationEntities
     */
    public void cascadeUpdate(Collection<Explanation> explanationEntities) {
        explanationEntities.forEach(entityManager::merge);
    }

    /**
     * Deletes an explanation
     * @param id the id of the explanation to delete
     */
    public void deleteById(Long id) {
        Explanation managedExplanationEntity = entityManager.find(Explanation.class, id);
        entityManager.remove(managedExplanationEntity);
    }

    /**
     * Adds the given collection of explanations in cascade
     *
     * @param explanationEntities
     */
    public void cascadeAdd(Collection<Explanation> explanationEntities) {
        explanationEntities.forEach(entityManager::merge);
    }
}
