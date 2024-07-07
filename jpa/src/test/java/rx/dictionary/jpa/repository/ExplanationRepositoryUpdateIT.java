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

    @Test
    public void cascadeUpdate_base() {
        //PREPARE
        final Set<Explanation> explanations = ITUtil.getAllExplanations(preparedStatementExecutor, "test");
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
        final Set<Explanation> updatedExplanations = ITUtil.getAllExplanations(preparedStatementExecutor, "test after update");
        assertTrue(updatedExplanations.stream().anyMatch(e -> "updated explanation".equals(e.getExplanation())));
    }
}
