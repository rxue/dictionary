package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplanationRepositoryDeleteIT extends AbstractDatabaseConfiguration {

    @BeforeAll
    public static void insert() {
        ExplanationRepositoryReadIT.insert();
    }
    @Test
    public void deleteExplanationById() {
        final Set<Explanation> explanations = ITUtil.getAllExplanations(preparedStatementExecutor, "test");
        final int originalExplanationSize = explanations.size();
        Explanation explanationToDelete = explanations.stream().findAny().get();
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.deleteById(explanationToDelete.getId());
        });
        //ASSERT
        Set<Explanation> explanationsAfterDelete = ITUtil.getAllExplanations(preparedStatementExecutor, "test");
        assertEquals(originalExplanationSize-1, explanationsAfterDelete.size());
    }
}
