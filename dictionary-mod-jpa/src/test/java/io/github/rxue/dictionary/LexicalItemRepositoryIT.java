package io.github.rxue.dictionary;

import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.*;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@QuarkusTest
class LexicalItemRepositoryIT {
    @Inject
    private EntityManager entityManager;
    private static LexicalItemRepository lexicalItemRepository;
    private static Session session;
    @Inject
    UserTransaction userTransaction;
    @BeforeAll
    public static void initOnce() {
        System.out.println("SECOND IT TEST");
    }

    @BeforeEach
    public void init() {
        if (lexicalItemRepository == null)
            lexicalItemRepository = new LexicalItemRepository(entityManager);
        if (session == null)
            session = entityManager.unwrap(Session.class);
        session.doWork(LexicalItemRepositoryIT::truncateTables);
    }

    private static void truncateTables(Connection connection) {
            List<String> deleteStatements = List.of(
                    "DELETE FROM explanation;--x",
                    "DELETE FROM LEXICAL_ITEM",
                    "TRUNCATE TABLE SENTENCE",
                    "ALTER TABLE Explanation ALTER COLUMN id RESTART WITH 1",
                    "ALTER SEQUENCE lexical_item_seq RESTART WITH 1");
            try (Statement batchStatement = connection.createStatement()) {
                deleteStatements.forEach(s -> {
                    try {
                        batchStatement.addBatch(s);
                    } catch (SQLException e) {
                        throw new IllegalArgumentException(e);
                    }
                });
                batchStatement.executeBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    @Test
    public void addLexicalItem() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        userTransaction.begin();
        LexicalItem lexicalItem = new LexicalItem();
        lexicalItem.setLanguage(Locale.US);
        lexicalItem.setValue("test");
        Long generatedId = lexicalItemRepository.addLexicalItem(lexicalItem);
        assertThat(generatedId, greaterThanOrEqualTo(0L));
        userTransaction.commit();
        assertNotNull(entityManager.find(LexicalItem.class, generatedId));
    }

    @Transactional
    @Test
    public void check() {
        List<LexicalItem> allLexicalItems = entityManager.createQuery("SELECT l FROM LexicalItem l")
                .getResultList();
        assertThat(allLexicalItems, hasSize(0));
    }
}
