package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.vo.Keyword;

import java.util.List;
import java.util.Locale;

public class LexicalItemRepository {
    private final EntityManager entityManager;
    public LexicalItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(LexicalItem lexicalItem) {
        entityManager.persist(lexicalItem);
    }

    public LexicalItem update(LexicalItem lexicalItem) {
        return entityManager.merge(lexicalItem);
    }

    public void deleteById(Long lexicialItemId) {
        final LexicalItem lexicalItemToDelete = entityManager.find(LexicalItem.class, lexicialItemId);
        entityManager.remove(lexicalItemToDelete);
    }
}
