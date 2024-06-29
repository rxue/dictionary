package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.AbstractDatabaseConfiguration;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.PartOfSpeech;

import java.sql.ResultSet;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static rx.dictionary.jpa.ITUtil.newExplanation;
import static rx.dictionary.jpa.ITUtil.newLexicalItem;

public class ExplanationRepositoryCreateIT extends AbstractDatabaseConfiguration {
    private static LexicalItem getAnyLexicalItem() {
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
        return existingSingleItem;
    }
    @Test
    public void create_base() {
        //ACT
        executeTransaction(entityManager -> {
            ExplanationRepository out = new ExplanationRepository(entityManager);
            LexicalItem lexicalItem = newLexicalItem(Locale.ENGLISH, "test");
            Explanation explanation = newExplanation(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试");
            explanation.setLexicalItem(lexicalItem);
            out.create(explanation);
        });
        LexicalItem createdLexicalItem = getAnyLexicalItem();
        assertNotNull(createdLexicalItem);
    }
}
