package rx.dictionary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("dictionary-mariadb");
            EntityManager em = emf.createEntityManager()) {
        }
    }
}
