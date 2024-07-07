package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.DictionaryEntry;
import rx.dictionary.jpa.entity.Explanation;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplanationRepositoryCreateIT extends AbstractDatabaseConfiguration {

    @Test
    public void testCreate() {
        //ACT
        userTransactionExecutor.execute(entityManager -> {
                    DictionaryEntry l = new DictionaryEntry();
                    l.setLanguage(Locale.ENGLISH);
                    l.setValue("take");
                    Explanation explanation = new Explanation(null, l);
                    explanation.setLanguage(Locale.SIMPLIFIED_CHINESE);
                    explanation.setExplanation("行动");
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.cascadeAdd(List.of(explanation));
        });
        //ASSERT
        final Explanation explanation = ITUtil.getAllExplanations(preparedStatementExecutor, "take")
                .stream().findAny().get();
        DictionaryEntry createdLexicalItem = explanation.getDictionaryEntry();
        assertEquals("take", createdLexicalItem.getValue());
        assertEquals(Locale.SIMPLIFIED_CHINESE, explanation.getLanguage());
        assertEquals("行动", explanation.getExplanation());

    }
}
