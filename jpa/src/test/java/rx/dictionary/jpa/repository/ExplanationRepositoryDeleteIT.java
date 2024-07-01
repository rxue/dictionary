package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.AbstractDatabaseConfiguration;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.PartOfSpeech;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rx.dictionary.jpa.ITUtil.newExplanationWithoutIds;
import static rx.dictionary.jpa.ITUtil.newLexicalItem;

public class ExplanationRepositoryDeleteIT extends AbstractDatabaseConfiguration {
    @BeforeEach
    public void addLexicalItems() {
        LexicalItem testLexicalItem = newLexicalItem(Locale.ENGLISH, "test");
        List<Explanation> explanations = new ArrayList<>();
        explanations.add(newExplanationWithoutIds(Locale.ENGLISH, PartOfSpeech.N, "test"));
        explanations.add(newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试"));
        explanations.add(newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "(医疗上的) 检查化验"));
        addLexicalItem(testLexicalItem, explanations);
    }

    private void addLexicalItem(LexicalItem lexicalItem, List<Explanation> explanations) {
        final Long lexicalItemId = preparedStatementExecutor.executeAndReturn("insert into lexical_item (language,value) value (?,?)", statement -> {
            statement.setString(1, lexicalItem.getLanguage().toString()); // Set value for column1
            statement.setString(2, lexicalItem.getValue()); // Set value for column2
            statement.executeUpdate(); //Execute the insert statement
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1); // Assuming the ID is a long (modify based on data type)
                } else {
                    throw new IllegalStateException("ID generation failure");
                }
            }
        });
        preparedStatementExecutor.execute("insert into explanation (id, lexical_item_id, language, partOfSpeech, definition) value (NEXT VALUE FOR explanation_id_seq,?,?,?,?)", statement -> {
            for (Explanation explanation : explanations) {
                statement.setLong(1, lexicalItemId); // Set value for column1
                statement.setString(2, explanation.getLanguage().toString());
                statement.setString(3, explanation.getPartOfSpeech().toString());
                statement.setString(4, explanation.getDefinition());
                statement.addBatch();
            }
            statement.executeBatch();
        });
    }

    @Test
    public void delete_oneExplantion() {
        //PREPARE
        LexicalItem existingLexicalItem = preparedStatementExecutor.executeAndReturn("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LexicalItem lexicalItem = new LexicalItem(resultSet.getLong("id"));
                    lexicalItem.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    lexicalItem.setValue(resultSet.getString("value"));
                    return lexicalItem;
                }
            }
            throw new IllegalArgumentException("");
        });
        List<Explanation> existingExplanations = preparedStatementExecutor.executeAndReturn("select * from explanation where language = ?", preparedStatement -> {
            List<Explanation> explanations = new ArrayList<>();
            preparedStatement.setString(1, Locale.SIMPLIFIED_CHINESE.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Explanation explanation = new Explanation(resultSet.getLong("id"));
                    final String languageLoaleString = resultSet.getString("language");
                    explanation.setLanguage(Locale.forLanguageTag(languageLoaleString.replace("_", "-")));
                    explanation.setLexicalItem(existingLexicalItem);
                    explanation.setPartOfSpeech(PartOfSpeech.valueOf(resultSet.getString("partofspeech")));
                    explanation.setDefinition(resultSet.getString("definition"));
                    explanations.add(explanation);
                }
            }
            return explanations;
        });
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            Explanation explanation = existingExplanations.get(0);
            ExplanationRepository out = new ExplanationRepositoryImpl(entityManager);
            out.deleteById(explanation.getId());
        });
        //ASSERT
        int explanationRows = ExplanationRepositoryCreateIT.countExplanationRows();
        assertEquals(2, explanationRows);
    }
}
