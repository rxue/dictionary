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
import static rx.dictionary.jpa.ITUtil.newExplanationWithoutIds;
import static rx.dictionary.jpa.ITUtil.newLexicalItem;

public class ExplanationRepositoryCreateIT extends AbstractDatabaseConfiguration {
    private static LexicalItem getAnyLexicalItem() {
        final LexicalItem existingSingleItem = preparedStatementExecutor.executeAndReturn("select * from lexical_item", preparedStatement -> {
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
            preparedStatementExecutor.execute("delete from " + table, preparedStatement -> {
                preparedStatement.execute();
            });
        }
    }
    @Test
    public void create_newLexicalItem() {
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            ExplanationRepository out = new ExplanationRepositoryImpl(entityManager);
            LexicalItem lexicalItem = newLexicalItem(Locale.ENGLISH, "test");
            Explanation explanation1 = newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试");
            explanation1.setLexicalItem(lexicalItem);
            Explanation explanation2 = newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试 2");
            explanation2.setLexicalItem(lexicalItem);
            out.create(List.of(explanation1, explanation2));
        });
        LexicalItem createdLexicalItem = getAnyLexicalItem();
        assertNotNull(createdLexicalItem);
        final int rows = countExplanationRows();
        assertEquals(2, rows);
    }
    @Test
    public void create_lexicalItemExisted() {
        //PREPARE
        preparedStatementExecutor.execute("insert into lexical_item (language,value) values ('EN','test')", preparedStatement -> {
            preparedStatement.execute();
        });
        final LexicalItem existingLexicalItem = getAnyLexicalItem();
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            ExplanationRepository out = new ExplanationRepositoryImpl(entityManager);
            Explanation explanation1 = newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试");
            explanation1.setLexicalItem(existingLexicalItem);
            Explanation explanation2 = newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试 2");
            explanation2.setLexicalItem(existingLexicalItem);
            out.create(List.of(explanation1, explanation2));
        });
        //ASSERT
        LexicalItem createdLexicalItem = getAnyLexicalItem();
        assertNotNull(createdLexicalItem);
        final int rows = countExplanationRows();
        assertEquals(2, rows);
    }

    static Integer countExplanationRows() {
        return preparedStatementExecutor.executeAndReturn("select * from explanation", prearedStatement -> {
            int rowCount = 0;
            try (ResultSet resultSet = prearedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rowCount++;
                }
            }
            return rowCount;
        });
    }
}
