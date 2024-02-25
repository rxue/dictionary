package rx.dictionary.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.*;

@ApplicationScoped
public class Resources {

    @PersistenceUnit(unitName = "dev-mariadb")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
