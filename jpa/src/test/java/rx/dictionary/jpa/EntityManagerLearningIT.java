package rx.dictionary.jpa;

import jakarta.persistence.*;
import jakarta.transaction.*;
import jakarta.transaction.RollbackException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.TestEntity;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerLearningIT extends AbstractDatabaseConfiguration {
    @AfterEach
    protected void purge() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            int deletedCount = em.createQuery("DELETE FROM Explanation e").executeUpdate();
            System.out.println("Number of entities deleted: " + deletedCount);
            deletedCount = em.createQuery("DELETE FROM LexicalItem e").executeUpdate();
            System.out.println("Number of entities deleted: " + deletedCount);
            transaction.commit();
        }
    }

    @Test
    public void testRollbackCreate() throws SystemException, NotSupportedException {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            TestEntity e = new TestEntity();
            UserTransaction tx = com.arjuna.ats.jta.UserTransaction.userTransaction();
            tx.begin();
            em.persist(e);
            tx.rollback();
        }
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            List<TestEntity> resultList = em.createQuery("select e from TestEntity e", TestEntity.class)
                    .getResultList();
            assertTrue(resultList.isEmpty());
        }

    }

    @Test
    public void testMerge() {
        LexicalItem l;
        LexicalItem managed;
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            l = initEntity();
            PersistenceUnitUtil util = entityManagerFactory.getPersistenceUnitUtil();
            assertNull(util.getIdentifier(l));
            managed = em.merge(l);
            assertNotNull(util.getIdentifier(l));
            assertNotNull(util.getIdentifier(managed));
            assertFalse(em.contains(l),"the initialized entity is not managed");
            assertTrue(em.contains(managed), "the returned entity by merge is managed");
            transaction.commit();
        }
        assertAll("",
                () -> assertFalse(managed == l),
                () -> assertTrue(Objects.equals(managed, l)));

    }

    @Test
    public void testPersist() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            LexicalItem l = initEntity();
            em.persist(l);
            assertTrue(em.contains(l), "Learned from JPA book 2023 > An entity is in persistent state if EntityManager.contains(e) returns true");
            transaction.commit();
        }
    }

    //@Test
    public void testRemove() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            LexicalItem l = initEntity();
            em.persist(l);
            transaction.commit();
        }
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            TypedQuery<LexicalItem> query = em.createQuery("SELECT e FROM LexicalItem e WHERE e.value = :value", LexicalItem.class);
            query.setParameter("value", "take");
            LexicalItem entityToRemove = query.getSingleResult();
            em.remove(entityToRemove);
            assertTrue(em.contains(entityToRemove));
            transaction.commit();
        }
    }
    @Test
    public void testFlushMode_DefaultAUTO() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            LexicalItem l = initEntity();
            em.persist(l);
            LexicalItem addedItem = em.find(LexicalItem.class, l.getId());
            assertNotNull(addedItem);
            transaction.commit();
        }
    }

    private static LexicalItem initEntity() {
        LexicalItem l = new LexicalItem();
        l.setLanguage(Locale.forLanguageTag("en"));
        l.setValue("take");
        Explanation explanation = new Explanation();

        explanation.setLanguage(Locale.forLanguageTag("en"));
        explanation.setExplanation("action of taking");
        l.addExplanation(explanation);
        return l;
    }

}
