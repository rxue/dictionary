package rx.dictionary.jpa.repository;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import rx.dictionary.jpa.entity.LexicalItemEntity;
import rx.dictionary.jpa.entity.ExplanationEntity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExplanationEntityRepositoryUpdateIT extends AbstractDatabaseConfiguration {

    @BeforeEach
    public void insert() {
        ExplanationEntityRepositoryReadIT.insert();
    }

    @AfterEach
    public void truncateTables() {
        ITUtil.truncateTables(preparedStatementExecutor);
    }

    @Test
    public void cascadeUpdate_base() {
        //PREPARE
        final List<ExplanationEntity> explanationEntities = ITUtil.getAllExplanations(preparedStatementExecutor, "test");
        ExplanationEntity explanationEntityToUpdate = explanationEntities.stream().findAny().get();
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            LexicalItemEntity lexicalItem = explanationEntityToUpdate.getLexicalItemEntity();
            lexicalItem.setValue("test after update");
            explanationEntityToUpdate.setDefinition("updated explanation");
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.cascadeUpdate(explanationEntities.stream().toList());
        });
        //ASSERT
        final List<ExplanationEntity> updatedExplanationEntities = ITUtil.getAllExplanations(preparedStatementExecutor, "test after update");
        assertEquals(3, updatedExplanationEntities.size());
        assertTrue(updatedExplanationEntities.stream().anyMatch(e -> "updated explanation".equals(e.getDefinition())));
    }
}
