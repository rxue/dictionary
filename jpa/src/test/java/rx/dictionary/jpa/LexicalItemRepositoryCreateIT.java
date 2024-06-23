package rx.dictionary.jpa;

import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.LexicalItemRepository;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LexicalItemRepositoryCreateIT extends AbstractDatabaseConfiguration {
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
    public void testCreate() {
        executeTransaction(entityManager -> {
                    LexicalItem l = new LexicalItem();
                    l.setLanguage(Locale.ENGLISH);
                    l.setValue("take");
                    Explanation explanation = new Explanation();
                    explanation.setLanguage(Locale.ENGLISH);
                    explanation.setExplanation("action of taking");
                    l.addExplanation(explanation);
            LexicalItemRepository out = new LexicalItemRepository(entityManager);
            out.add(l);
        });
        //ASSERT
        final LexicalItem createdLexicalItem = getLexicalItem();
        assertEquals("take", createdLexicalItem.getValue());
        assertEquals("action of taking", createdLexicalItem.getExplanations().stream().findAny().get().getExplanation());
    }
}
