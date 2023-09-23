package rx.dictionary;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import rx.dictionary.jpaentity.LexicalItem;

public class LexicalItemService {
    @Inject
    private LexicalItemRepository repo;
    @Transactional
    public void create(LexicalItem lexicalItem) {
        repo.create(lexicalItem);
    }
}
