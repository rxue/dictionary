package rx.dictionary.jpa;

import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.DictionaryEntry;
import rx.dictionary.jpa.repository.DictionaryEntryRepository;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryEntryRepositoryCreateIT extends AbstractDatabaseConfiguration {

    @Test
    public void testCreate() {
        //ACT
        userTransactionExecutor.execute(entityManager -> {
                    DictionaryEntry l = new DictionaryEntry();
                    l.setLanguage(Locale.ENGLISH);
                    l.setValue("take");
                    Explanation explanation = new Explanation();
                    explanation.setLanguage(Locale.SIMPLIFIED_CHINESE);
                    explanation.setExplanation("行动");
                    l.addExplanation(explanation);
            DictionaryEntryRepository out = new DictionaryEntryRepository(entityManager);
            out.add(l);
        });
        //ASSERT
        final DictionaryEntry createdLexicalItem = ITUtil.getFirstLexicalItem(preparedStatementExecutor);
        assertEquals("take", createdLexicalItem.getValue());
        Explanation explanation = createdLexicalItem.getExplanations().stream().findAny().get();
        assertEquals(Locale.SIMPLIFIED_CHINESE, explanation.getLanguage());
        assertEquals("行动", explanation.getExplanation());
    }
}
