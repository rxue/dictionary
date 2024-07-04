package rx.dictionary.jpa;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import rx.dictionary.jpa.entity.DictionaryEntry;
import rx.dictionary.jpa.entity.Explanation;

import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.repository.DictionaryEntryRepository;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryEntryRepositoryUpdateIT extends AbstractDatabaseConfiguration {

    @BeforeEach
    public void addLexicalItem() {
        final Long generatedId = preparedStatementExecutor.executeAndReturn("insert into lexical_item (language,value) value (?,?)", statement -> {
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
        preparedStatementExecutor.execute("insert into explanation (id, lexical_item_id, language, partOfSpeech, explanation) value (NEXT VALUE FOR explanation_id_seq,?,?,?,?)", statement -> {
            statement.setLong(1, generatedId); // Set value for column1
            statement.setString(2, Locale.SIMPLIFIED_CHINESE.toLanguageTag());
            statement.setString(3, "N");
            statement.setString(4, "测试");
            statement.executeUpdate();
        });
    }

    @AfterEach
    public void removeLexicalItem() {
        preparedStatementExecutor.execute("delete from explanation", statement -> {
            statement.executeUpdate();
        });
        preparedStatementExecutor.execute("delete from lexical_item", statement -> {
            statement.executeUpdate();
        });
        preparedStatementExecutor.execute("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                assertFalse(resultSet.next());
            }
        });
        preparedStatementExecutor.execute("select * from explanation", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                assertFalse(resultSet.next());
            }
        });
    }
    private DictionaryEntry getLexicalItem() {
        final DictionaryEntry existingSingleItem = preparedStatementExecutor.executeAndReturn("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    DictionaryEntry lexicalItem = new DictionaryEntry(resultSet.getLong("id"));
                    lexicalItem.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    lexicalItem.setValue(resultSet.getString("value"));
                    return lexicalItem;
                }
                throw new IllegalArgumentException(preparedStatement + " does not return any result");
            }
        });
        final Set<Explanation> explanations = preparedStatementExecutor.executeAndReturn("select * from explanation where lexical_item_id = ?", preparedStatement -> {
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
        final DictionaryEntry existingSingleItem = getLexicalItem();
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            existingSingleItem.setValue("lop changed");
            Set<Explanation> explanationsToUpdate = existingSingleItem.getExplanations();
            Explanation explanation = explanationsToUpdate.stream().findAny().get();
            explanation.setExplanation("updated explanation");
            DictionaryEntryRepository out = new DictionaryEntryRepository(entityManager);
            out.update(existingSingleItem);
        });
        //ASSERT
        final DictionaryEntry updatedLexicalItem = getLexicalItem();
        assertEquals("lop changed", updatedLexicalItem.getValue());
        assertEquals("updated explanation", updatedLexicalItem.getExplanations().stream().findAny().get().getExplanation());
    }
    @Test
    public void update_addNewItem() {
        final DictionaryEntry existingSingleItem = getLexicalItem();
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            existingSingleItem.setValue("lop changed");
            Set<Explanation> explanationsToUpdate = existingSingleItem.getExplanations();
            Explanation newExplanation = new Explanation();
            newExplanation.setLanguage(Locale.ENGLISH);
            newExplanation.setExplanation("new explanation");
            explanationsToUpdate.add(newExplanation);
            DictionaryEntryRepository out = new DictionaryEntryRepository(entityManager);
            out.update(existingSingleItem);
        });
        //ASSERT
        final DictionaryEntry updatedLexicalItem = getLexicalItem();
        assertEquals("lop changed", updatedLexicalItem.getValue());
        assertEquals(2, updatedLexicalItem.getExplanations().size());
    }

    @Test
    public void update_removeExistingItem() {
        final DictionaryEntry existingSingleItem = getLexicalItem();
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            existingSingleItem.setValue("lop changed");
            Set<Explanation> explanationsToUpdate = existingSingleItem.getExplanations();
            explanationsToUpdate.clear();
            DictionaryEntryRepository out = new DictionaryEntryRepository(entityManager);
            out.update(existingSingleItem);
        });
        //ASSERT
        final DictionaryEntry updatedLexicalItem = getLexicalItem();
        assertEquals("lop changed", updatedLexicalItem.getValue());
        assertTrue(updatedLexicalItem.getExplanations().isEmpty());
    }
}
