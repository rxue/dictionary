package rx.dictionary.jpa.repository;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import rx.dictionary.jpa.entity.DictionaryEntry;
import rx.dictionary.jpa.entity.Explanation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExplanationRepositoryUpdateIT extends AbstractDatabaseConfiguration {

    @BeforeEach
    public void insert() {
        ExplanationRepositoryReadIT.insert();
    }

    @AfterEach
    public void truncate() {
        preparedStatementExecutor.execute("delete from explanation", statement -> {
            statement.executeUpdate();
        });
        preparedStatementExecutor.execute("delete from lexical_item", statement -> {
            statement.executeUpdate();
        });
        //ensure truncate success
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
                    Explanation explanation = new Explanation(resultSet.getLong("id"), existingSingleItem);
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
    public void cascadeUpdate_base() {
        //PREPARE
        final Set<Explanation> explanations = ITUtil.getLexicalItem(preparedStatementExecutor, "test")
                .getExplanations();
        Explanation explanationToUpdate = explanations.stream().findAny().get();
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            DictionaryEntry dictionaryEntry = explanationToUpdate.getDictionaryEntry();
            dictionaryEntry.setValue("test after update");
            explanationToUpdate.setExplanation("updated explanation");
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.cascadeUpdate(explanations.stream().toList());
        });
        //ASSERT
        final Set<Explanation> updatedExplanations = ITUtil.getLexicalItem(preparedStatementExecutor, "test after update")
                .getExplanations();
        assertTrue(updatedExplanations.stream().anyMatch(e -> "updated explanation".equals(e.getExplanation())));
    }
}
