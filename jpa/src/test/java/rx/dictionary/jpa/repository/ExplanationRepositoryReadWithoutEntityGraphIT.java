package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.AbstractDatabaseConfiguration;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.jpa.repository.input.Keyword;

import java.sql.ResultSet;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static rx.dictionary.jpa.ITUtil.newExplanationWithoutIds;
import static rx.dictionary.jpa.ITUtil.newLexicalItem;

public class ExplanationRepositoryReadWithoutEntityGraphIT extends AbstractDatabaseConfiguration {
    @BeforeEach
    public void addLexicalItems() {
        LexicalItem testLexicalItem = newLexicalItem(Locale.ENGLISH, "test");
        List<Explanation> explanations = new ArrayList<>();
        explanations.add(newExplanationWithoutIds(Locale.ENGLISH, PartOfSpeech.N, "test"));
        explanations.add(newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "测试"));
        explanations.add(newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "(医疗上的) 检查化验"));
        addLexicalItem(testLexicalItem, explanations);

        LexicalItem testLexicalItem2 = newLexicalItem(Locale.ENGLISH, "testimony");
        List<Explanation> explanations2 = new ArrayList<>();
        explanations2.add(newExplanationWithoutIds(Locale.SIMPLIFIED_CHINESE, PartOfSpeech.N, "证据，证明"));
        addLexicalItem(testLexicalItem2, explanations2);

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
    public void findLike_prepbase() {
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            ExplanationRepositoryImplWithoutEntityGraph out = new ExplanationRepositoryImplWithoutEntityGraph(entityManager);
            List<Explanation> result = out.findLike(new Keyword("test%", Locale.ENGLISH), Locale.SIMPLIFIED_CHINESE);
            assertEquals(3, result.size());
            assertAll(() -> {
                Explanation chineseExplanation = result.get(0);
                assertEquals(Locale.SIMPLIFIED_CHINESE, chineseExplanation.getLanguage());
                assertEquals(PartOfSpeech.N, chineseExplanation.getPartOfSpeech());
            });
        });
    }
}
