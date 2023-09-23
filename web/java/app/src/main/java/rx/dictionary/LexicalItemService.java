package rx.dictionary;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import rx.dictionary.jpaentity.LexicalItem;
@Stateless
public class LexicalItemService {
    private EntityManagerFactory emf;
    public void create(LexicalItem lexicalItem) {
        emf = Persistence.createEntityManagerFactory("primary");
        EntityManager em = emf.createEntityManager();
        LexicalItemRepository repo = new LexicalItemRepository(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        repo.create(lexicalItem);
        transaction.commit();
    }
}
