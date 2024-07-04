package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.DictionaryEntry;

public class DictionaryEntryRepository {
    private final EntityManager entityManager;
    public DictionaryEntryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void add(DictionaryEntry lexicalItem) {
        entityManager.persist(lexicalItem);
    }

    public DictionaryEntry update(DictionaryEntry lexicalItem) {
        return entityManager.merge(lexicalItem);
    }

    public void deleteById(Long lexicialItemId) {
        final DictionaryEntry lexicalItemToDelete = entityManager.find(DictionaryEntry.class, lexicialItemId);
        entityManager.remove(lexicalItemToDelete);
    }
}
