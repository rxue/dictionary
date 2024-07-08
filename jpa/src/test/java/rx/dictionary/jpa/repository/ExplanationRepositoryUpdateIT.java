package rx.dictionary.jpa.repository;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import rx.dictionary.jpa.entity.LexicalItemEntity;
import rx.dictionary.jpa.entity.Explanation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExplanationRepositoryUpdateIT extends AbstractDatabaseConfiguration {

    @BeforeEach
    public void insert() {
        ExplanationRepositoryReadIT.insert();
    }

    @AfterEach
    public void truncateTables() {
        ITUtil.truncateTables(preparedStatementExecutor);
    }

    @Test
    public void cascadeUpdate_base() {
        //PREPARE
        final Set<Explanation> explanations = ITUtil.getAllExplanations(preparedStatementExecutor, "test");
        Explanation explanationToUpdate = explanations.stream().findAny().get();
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            LexicalItemEntity lexicalItem = explanationToUpdate.getLexicalItemEntity();
            lexicalItem.setValue("test after update");
            explanationToUpdate.setDefinition("updated explanation");
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.cascadeUpdate(explanations.stream().toList());
        });
        //ASSERT
        final Set<Explanation> updatedExplanations = ITUtil.getAllExplanations(preparedStatementExecutor, "test after update");
        assertTrue(updatedExplanations.stream().anyMatch(e -> "updated explanation".equals(e.getDefinition())));
    }
}
