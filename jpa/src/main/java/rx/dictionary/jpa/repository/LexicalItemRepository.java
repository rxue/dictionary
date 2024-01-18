package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.vo.Keyword;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class LexicalItemRepository {
    private final EntityManager entityManager;
    public LexicalItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<LexicalItem> findByKeywordStartWith(Keyword startWithKeyword, Locale explanationLanguage) {
        return entityManager.createQuery("SELECT item FROM LexicalItem item WHERE item.language = :language AND item.value LIKE :value")
                .setParameter("language", startWithKeyword.language())
                .setParameter("value", startWithKeyword.value() + "%")
                .getResultList();
    }
    public void add(LexicalItem lexicalItem) {
        entityManager.persist(lexicalItem);
        Set<Explanation> explanations = lexicalItem.getExplanations();
        setLexicalItemIdInExplanations(lexicalItem.getId(), lexicalItem.getExplanations());
        for (Explanation explanation : explanations)
            entityManager.persist(explanation);
    }
    private static void setLexicalItemIdInExplanations(Long lexicalItemId, Set<Explanation> explanations) {
        for (Explanation explanation : explanations) {
            explanation.setLexicalItemID(lexicalItemId);
        }
    }
}
