package rx.dictionary.jpa;

import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.DictionaryEntry;
import rx.dictionary.jpa.repository.DictionaryEntryRepository;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static rx.dictionary.jpa.ITUtil.localeStringToTag;

public class DictionaryEntryRepositoryCreateIT extends AbstractDatabaseConfiguration {
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
                    String localeTag = localeStringToTag(resultSet.getString("language"));
                    explanation.setLanguage(Locale.forLanguageTag(localeTag));
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
        final DictionaryEntry createdLexicalItem = getLexicalItem();
        assertEquals("take", createdLexicalItem.getValue());
        Explanation explanation = createdLexicalItem.getExplanations().stream().findAny().get();
        assertEquals(Locale.SIMPLIFIED_CHINESE, explanation.getLanguage());
        assertEquals("行动", explanation.getExplanation());
    }
}
