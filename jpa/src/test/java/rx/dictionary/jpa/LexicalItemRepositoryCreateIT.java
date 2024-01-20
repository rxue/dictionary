package rx.dictionary.jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.LexicalItemRepository;

import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LexicalItemRepositoryCreateIT extends AbstractDatabaseConfiguration {
    static void insert() {
        executeTransaction(repo -> {
                    LexicalItem l = new LexicalItem();
                    l.setLanguage(Locale.forLanguageTag("en"));
                    l.setValue("take");
                    Explanation explanation = new Explanation();
                    explanation.setLanguage(Locale.forLanguageTag("en"));
                    explanation.setExplanation("action of taking");
                    l.addExplanation(explanation);
                    repo.add(l);
        }
        );
    }
    @AfterEach
    public void truncate() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.createQuery("DELETE FROM Explanation")
                    .executeUpdate();
            em.createQuery("DELETE FROM LexicalItem")
                    .executeUpdate();
            transaction.commit();
        }
    }

    private static void executeTransaction(Consumer<LexicalItemRepository> operations)  {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            operations.accept(new LexicalItemRepository(em));
            transaction.commit();
        }
    }


    @Test
    public void testCreate() {
        insert();
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            LexicalItem lexicalItem = em.find(LexicalItem.class, 1);
            assertAll("",
                    () -> {
                        assertNotNull(lexicalItem);
                        assertNotNull(lexicalItem.getId());
                    },
                    () -> {
                        Set<Explanation> explanations = lexicalItem.getExplanations();
                        Explanation explanation = explanations.stream().toList().get(0);
                        assertNotNull(explanation);
                        assertNotNull(explanation.getId());
                    });
        }
    }
    //@Test
    public void testAddMultipleExplanations() {
        insert();
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            LexicalItem lexicalItem = em.find(LexicalItem.class, 1);
            assertAll("",
                    () -> {
                        assertNotNull(lexicalItem);
                        assertNotNull(lexicalItem.getId());
                    },
                    () -> {
                        Set<Explanation> explanations = lexicalItem.getExplanations();
                        Explanation explanation = explanations.stream().toList().get(0);
                        assertNotNull(explanation);
                        assertNotNull(explanation.getId());
                    });
        }
    }
}
