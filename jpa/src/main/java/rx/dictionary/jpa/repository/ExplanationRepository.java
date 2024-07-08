package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.data.LexicalItem;
import rx.dictionary.jpa.entity.Explanation;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ExplanationRepository {
    private final EntityManager entityManager;
    public ExplanationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Explanation> findLike(LexicalItem keyword, Locale definitionLanguage) {
        String jpql = "select e from Explanation e where " +
                "e.lexicalItemEntity.language =: language and " +
                "e.lexicalItemEntity.value like :value and " +
                "e.language =: definitionLanguage";
        return entityManager.createQuery(jpql, Explanation.class)
                .setParameter("language", keyword.getLanguage())
                .setParameter("value", keyword.getValue() + "%")
                .setParameter("definitionLanguage", definitionLanguage)
                .getResultList();
    }

    public void cascadeUpdate(Collection<Explanation> explanations) {
        explanations.forEach(entityManager::merge);
    }

    public void deleteById(Long id) {
        Explanation managedExplanation = entityManager.find(Explanation.class, id);
        entityManager.remove(managedExplanation);
    }

    public void cascadeAdd(Collection<Explanation> explanations) {
        explanations.forEach(entityManager::merge);
    }
}
