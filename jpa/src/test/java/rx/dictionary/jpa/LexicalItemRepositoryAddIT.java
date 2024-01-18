package rx.dictionary.jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.LexicalItemRepository;

import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LexicalItemRepositoryAddIT extends AbstractDatabaseConfiguration {
    static void insert() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            LexicalItemRepository repository = new LexicalItemRepository(em);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            System.out.println("Insert:::::::::::::::::");
            //
            LexicalItem l = new LexicalItem();
            l.setLanguage(Locale.forLanguageTag("en"));
            l.setValue("take");
            Explanation explanation = new Explanation();
            explanation.setLanguage(Locale.forLanguageTag("en"));
            explanation.setExplanation("action of taking");
            l.setExplanations(Set.of(explanation));
            repository.add(l);
            //
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
}
