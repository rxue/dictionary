package rx.dictionary;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpaentity.LexicalItem;

public class LexicalItemRepository {
    private EntityManager em;
    public LexicalItemRepository(EntityManager em) {
        this.em = em;
    }
    public void create(LexicalItem lexicalItem) {
        em.persist(lexicalItem);
    }
}
