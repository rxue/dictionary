package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.Optional;

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

    public Optional<LexicalItem> find(Keyword test) {
        LexicalItem result = entityManager.createQuery("select l from LexicalItem l where l.language =: language and l.value =: value", LexicalItem.class)
                .setParameter("language", test.language())
                .setParameter("value", test.value())
                .getSingleResult();
        return Optional.ofNullable(result);
    }
}
