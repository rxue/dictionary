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

public class ExplanationRepositoryUpdateIT extends AbstractDatabaseConfiguration {
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
        final Long lexicalItemId = executeStatementWithReturnValue("insert into lexical_item (language,value) value (?,?)", statement -> {
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
        executeTransaction("insert into explanation (id, lexical_item_id, language, partOfSpeech, definition) value (NEXT VALUE FOR explanation_id_seq,?,?,?,?)", statement -> {
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
    public void update_onlyExplantion() {
        //PREPARE
        LexicalItem existingLexicalItem = executeStatementWithReturnValue("select * from lexical_item", preparedStatement -> {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LexicalItem lexicalItem = new LexicalItem(resultSet.getLong("id"));
                    lexicalItem.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    lexicalItem.setValue(resultSet.getString("value"));
                    return lexicalItem;
                }
            }
            throw new IllegalArgumentException("");
        });
        List<Explanation> existingExplanations = executeStatementWithReturnValue("select * from explanation where language = ?", preparedStatement -> {
            List<Explanation> explanations = new ArrayList<>();
            preparedStatement.setString(1, Locale.SIMPLIFIED_CHINESE.toString());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    Explanation explanation = new Explanation(resultSet.getLong("id"));
                    final String languageLoaleString = resultSet.getString("language");
                    explanation.setLanguage(Locale.forLanguageTag(languageLoaleString.replace("_","-")));
                    explanation.setLexicalItem(existingLexicalItem);
                    //explanation.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    explanation.setPartOfSpeech(PartOfSpeech.valueOf(resultSet.getString("partofspeech")));
                    explanation.setDefinition(resultSet.getString("definition"));
                    explanations.add(explanation);
                }
            }
            return explanations;
        });
        //ACT
        executeTransaction(entityManager -> {
            Explanation explanation1 = existingExplanations.get(0);
            explanation1.setDefinition("updated def 1");
            Explanation explanation2 = existingExplanations.get(1);
            explanation2.setDefinition("updated def 2");
            ExplanationRepository out = new ExplanationRepositoryImpl(entityManager);
            out.update(existingExplanations);
        });
        //ASSERT
        List<Explanation> updatedResult = executeStatementWithReturnValue("select * from explanation where language = ?", preparedStatement -> {
                preparedStatement.setString(1, Locale.SIMPLIFIED_CHINESE.toString());
                List<Explanation> result = new ArrayList<>();
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println("language is " + resultSet.getString("language"));
                        Explanation explanation = new Explanation(resultSet.getLong("id"));
                        explanation.setDefinition(resultSet.getString("definition"));
                        result.add(explanation);
                    }
                }
                return result;
        });
        assertEquals(List.of("updated def 1", "updated def 2"), updatedResult.stream().map(Explanation::getDefinition).toList());
    }
}
