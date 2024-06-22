package rx.dictionary.jpa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.transaction.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rx.Util;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.LexicalItemRepository;

import static org.junit.jupiter.api.Assertions.*;

public class LexicalItemRepositoryUpdateIT extends AbstractDatabaseConfiguration {
    private static final String SQL_EXCEPTION_ERROR_MESSAGE = SQLException.class + " thrown when executing operations on PreparedStatement, program terminates now!";
    @BeforeEach
    public void addLexicalItem() {
        final Long generatedId = executeTransactionWithReturnValue("insert into lexical_item (language,value) value (?,?)", statement -> {
            statement.setString(1, Locale.ENGLISH.toLanguageTag()); // Set value for column1
            statement.setString(2, "test"); // Set value for column2
            statement.executeUpdate(); //Execute the insert statement
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1); // Assuming the ID is a long (modify based on data type)
                } else {
                    throw new IllegalStateException("ID generation failure");
                }
            }
        });
        executeTransaction("insert into explanation (id, lexical_item_id, language, partOfSpeech, explanation) value (NEXT VALUE FOR explanation_id_seq,?,?,?,?)", statement -> {
            statement.setLong(1, generatedId); // Set value for column1
            statement.setString(2, Locale.SIMPLIFIED_CHINESE.toLanguageTag());
            statement.setString(3, "N");
            statement.setString(4, "测试");
            statement.executeUpdate();
        });
    }

    @AfterEach
    public void removeLexicalItem() {
        executeTransaction("delete from explanation", statement -> {
            statement.executeUpdate();
        });
        executeTransaction("delete from lexical_item", statement -> {
            statement.executeUpdate();
        });
        executeTransaction("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                assertFalse(resultSet.next());
            }
        });
        executeTransaction("select * from explanation", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                assertFalse(resultSet.next());
            }
        });
    }

    private static void executeTransaction(Consumer<EntityManager> operations) {
        UserTransaction tx = Util.userTransaction();
        try {
            tx.begin();
        } catch (NotSupportedException | SystemException e) {
            throw new RuntimeException("Transaction fails to begin", e);
        }
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            operations.accept(entityManager);
        }
        try {
            tx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException e) {
            throw new RuntimeException("Transaction fails to commit", e);
        }
    }
    private LexicalItem getLexicalItem() {
        final LexicalItem existingSingleItem = executeTransactionWithReturnValue("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LexicalItem lexicalItem = new LexicalItem(resultSet.getLong("id"));
                    lexicalItem.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    lexicalItem.setValue(resultSet.getString("value"));
                    return lexicalItem;
                }
                throw new IllegalArgumentException(preparedStatement + " does not return any result");
            }
        });
        final Set<Explanation> explanations = executeTransactionWithReturnValue("select * from explanation where lexical_item_id = ?", preparedStatement -> {
            preparedStatement.setLong(1, existingSingleItem.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Explanation> result = new HashSet<>();
                while (resultSet.next()) {
                    Explanation explanation = new Explanation(resultSet.getLong("id"));
                    explanation.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    explanation.setExplanation(resultSet.getString("explanation"));
                    result.add(explanation);
                }
                return result;
            }
        });
        explanations.forEach(existingSingleItem::addExplanation);
        return existingSingleItem;
    }

    @Test
    public void update_base() {
        final LexicalItem existingSingleItem = getLexicalItem();
        //ACT
        executeTransaction(entityManager -> {
            existingSingleItem.setValue("lop changed");
            Set<Explanation> explanationsToUpdate = existingSingleItem.getExplanations();
            Explanation explanation = explanationsToUpdate.stream().findAny().get();
            explanation.setExplanation("updated explanation");
            LexicalItemRepository out = new LexicalItemRepository(entityManager);
            out.update(existingSingleItem);
        });
        //ASSERT
        final LexicalItem updatedLexicalItem = getLexicalItem();
        assertEquals("lop changed", updatedLexicalItem.getValue());
        assertEquals("updated explanation", updatedLexicalItem.getExplanations().stream().findAny().get().getExplanation());
    }
    @Test
    public void update_addNewItem() {
        final LexicalItem existingSingleItem = getLexicalItem();
        //ACT
        executeTransaction(entityManager -> {
            existingSingleItem.setValue("lop changed");
            Set<Explanation> explanationsToUpdate = existingSingleItem.getExplanations();
            Explanation newExplanation = new Explanation();
            newExplanation.setLanguage(Locale.ENGLISH);
            newExplanation.setExplanation("new explanation");
            explanationsToUpdate.add(newExplanation);
            LexicalItemRepository out = new LexicalItemRepository(entityManager);
            out.update(existingSingleItem);
        });
        //ASSERT
        final LexicalItem updatedLexicalItem = getLexicalItem();
        assertEquals("lop changed", updatedLexicalItem.getValue());
        assertEquals(2, updatedLexicalItem.getExplanations().size());
    }

    @Test
    public void update_removeExistingItem() {
        final LexicalItem existingSingleItem = getLexicalItem();
        //ACT
        executeTransaction(entityManager -> {
            existingSingleItem.setValue("lop changed");
            Set<Explanation> explanationsToUpdate = existingSingleItem.getExplanations();
            explanationsToUpdate.clear();
            LexicalItemRepository out = new LexicalItemRepository(entityManager);
            out.update(existingSingleItem);
        });
        //ASSERT
        final LexicalItem updatedLexicalItem = getLexicalItem();
        assertEquals("lop changed", updatedLexicalItem.getValue());
        assertTrue(updatedLexicalItem.getExplanations().isEmpty());
    }

}
