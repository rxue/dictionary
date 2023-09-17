package rx.dictionary.util;

import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class Resources {
    @Produces
    @PersistenceContext
    private EntityManager em;
}
