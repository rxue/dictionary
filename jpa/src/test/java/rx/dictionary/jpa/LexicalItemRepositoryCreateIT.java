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

import static org.junit.jupiter.api.Assertions.*;
import static rx.dictionary.jpa.ITUtil.executeTransaction;

public class LexicalItemRepositoryCreateIT extends AbstractDatabaseConfiguration {
    /**
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

    @Test
    public void testCreate() {
        executeTransaction(entityManagerFactory, repo -> {
                    LexicalItem l = new LexicalItem();
                    l.setLanguage(Locale.ENGLISH);
                    l.setValue("take");
                    Explanation explanation = new Explanation();
                    explanation.setLanguage(Locale.ENGLISH);
                    explanation.setExplanation("action of taking");
                    l.addExplanation(explanation);
                    repo.add(l);
                }
        );
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            LexicalItem lexicalItem = ITUtil.getSingleLexicalItem(em);
            assertAll("",
                    () -> {
                        assertNotNull(lexicalItem);
                        //assertNotNull(lexicalItem.getId());
                    },
                    () -> {
                        Set<Explanation> explanations = lexicalItem.getExplanations();
                        Explanation explanation = explanations.stream().toList().get(0);
                        assertNotNull(explanation);
                        assertNotNull(explanation.getId());
                    });
        }
    }
    @Test
    public void testAddMultipleExplanations() {
        executeTransaction(entityManagerFactory, repo -> {
            LexicalItem l = new LexicalItem();
            l.setLanguage(Locale.ENGLISH);
            l.setValue("take");
            Explanation explanation = new Explanation();
            explanation.setLanguage(Locale.ENGLISH);
            explanation.setExplanation("action of taking");
            l.addExplanation(explanation);
            Explanation explanation2 = new Explanation();
            explanation2.setLanguage(Locale.forLanguageTag("en"));
            explanation2.setExplanation("action of taking 2");
            l.addExplanation(explanation2);
            repo.add(l);
        });
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            LexicalItem lexicalItem = ITUtil.getSingleLexicalItem(em);
            assertAll("",
                    () -> {
                        assertNotNull(lexicalItem);
                        //assertNotNull(lexicalItem.getId());
                    },
                    () -> {
                        Set<Explanation> explanations = lexicalItem.getExplanations();
                        assertEquals(2, explanations.size());
                    });
        }
    }*/
}
