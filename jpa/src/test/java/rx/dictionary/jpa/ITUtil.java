package rx.dictionary.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.LexicalItemRepository;

import java.util.function.Consumer;

public class ITUtil {
    private ITUtil() {
    }

    static LexicalItem getSingleLexicalItem(EntityManager em) {
        return em.createQuery("SELECT l FROM LexicalItem l", LexicalItem.class)
                .getSingleResult();
    }
    static void executeTransaction(EntityManagerFactory entityManagerFactory, Consumer<LexicalItemRepository> operations)  {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            operations.accept(new LexicalItemRepository(em));
            transaction.commit();
        }
        System.out.println("transaction committed");
    }
}