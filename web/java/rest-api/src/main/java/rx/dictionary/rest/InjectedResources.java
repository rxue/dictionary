package rx.dictionary.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@ApplicationScoped
public class InjectedResources {

    @PersistenceUnit(unitName = "dev-mariadb")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
