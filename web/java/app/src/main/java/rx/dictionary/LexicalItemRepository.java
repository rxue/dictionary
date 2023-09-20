package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import rx.dictionary.jpaentity.LexicalItem;

public class LexicalItemRepository {
    @Inject
    private EntityManager em;
    public void create(LexicalItem lexicalItem) {
        em.persist(lexicalItem);
    }
}
