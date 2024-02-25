package rx.dictionary.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.*;

@ApplicationScoped
public class Resources {
    //@Produces
    @PersistenceContext(unitName = "dev-mariadb")
    private EntityManager em;

    @PersistenceUnit(unitName = "dev-mariadb")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
