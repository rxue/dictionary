package rx.dictionary.jpa;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntityManagerTest extends AbstractDatabaseConfiguration {
    @Test
    public void testEntity() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            assertNotNull(em);
        }
    }
}
