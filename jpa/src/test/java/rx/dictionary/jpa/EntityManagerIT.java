package rx.dictionary.jpa;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.LexicalItem;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntityManagerIT extends AbstractDatabaseConfiguration {

    @Test
    public void testEntity() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            LexicalItem l = new LexicalItem();
            em.persist(l);
            assertNotNull(em);
        }
    }
}
