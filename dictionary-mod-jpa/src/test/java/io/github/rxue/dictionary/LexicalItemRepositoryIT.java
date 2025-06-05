package io.github.rxue.dictionary;

import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        session.doWork(connection -> {
            List<String> deleteStatements = List.of("DELETE FROM explanation", "DELETE FROM LEXICAL_ITEM", "TRUNCATE TABLE SENTENCE");
            try (Statement batchStatement = connection.createStatement()) {
                deleteStatements.forEach(s -> {
                    try {
                        batchStatement.addBatch(s);
                    } catch (SQLException e) {
                        throw new IllegalArgumentException(e);
                    }
                });
                batchStatement.executeBatch();
            }

        });
    }
    @AfterAll
    public static void clean() {
        session.close();
    }

    @Transactional
    @Test
    public void addLexicalItem() {
        LexicalItem lexicalItem = new LexicalItem();
        lexicalItem.setLanguage(Locale.US);
        lexicalItem.setValue("test");
        Long generatedId = lexicalItemRepository.addLexicalItem(lexicalItem);
        assertThat(generatedId, greaterThanOrEqualTo(0L));
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
