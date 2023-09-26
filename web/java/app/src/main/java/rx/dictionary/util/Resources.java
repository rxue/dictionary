package rx.dictionary.util;

import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

public class Resources {
    @Produces
    @PersistenceContext(unitName = "dev-mariadb")
    private EntityManager em;
}
