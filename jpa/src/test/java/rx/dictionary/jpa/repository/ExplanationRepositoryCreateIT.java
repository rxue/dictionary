package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.AbstractDatabaseConfiguration;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.PartOfSpeech;

import java.sql.*;
import java.util.List;
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
    @AfterEach
    public void truncateTables() {
        List<String> tableNames = List.of("explanation", "lexical_item");
        for (String table : tableNames) {
            executeTransaction("delete from " + table, preparedStatement -> {
                preparedStatement.execute();
            });
        }
    }
    @Test
    public void create_newLexicalItem() {
        //ACT
        executeTransaction(entityManager -> {
            ExplanationRepository out = new ExplanationRepositoryImplWithoutEntityGraph(entityManager);
            LexicalItem lexicalItem = newLexicalItem(Locale.ENGLISH, "test");
            Explanation explanation1 = newExplanation(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试");
            explanation1.setLexicalItem(lexicalItem);
            Explanation explanation2 = newExplanation(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试 2");
            explanation2.setLexicalItem(lexicalItem);
            out.create(List.of(explanation1, explanation2));
        });
        LexicalItem createdLexicalItem = getAnyLexicalItem();
        assertNotNull(createdLexicalItem);
        executeQuery("select * from explanation", resultSet -> {
                int rowCount = 0;
                while (resultSet.next())
                    rowCount++;
                assertEquals(2, rowCount);
        });
    }
    @Test
    public void create_lexicalItemExisted() {
        //PREPARE
        executeTransaction("insert into lexical_item (language,value) values ('EN','test')", preparedStatement -> {
            preparedStatement.execute();
        });
        final LexicalItem existingLexicalItem = getAnyLexicalItem();
        //ACT
        executeTransaction(entityManager -> {
            ExplanationRepository out = new ExplanationRepositoryImpl(entityManager);
            Explanation explanation1 = newExplanation(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试");
            explanation1.setLexicalItem(existingLexicalItem);
            Explanation explanation2 = newExplanation(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试 2");
            explanation2.setLexicalItem(existingLexicalItem);
            out.create(List.of(explanation1, explanation2));
        });
        LexicalItem createdLexicalItem = getAnyLexicalItem();
        assertNotNull(createdLexicalItem);
        executeQuery("select * from explanation", resultSet -> {
            int rowCount = 0;
            while (resultSet.next())
                rowCount++;
            assertEquals(2, rowCount);
        });
    }
}
