package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rx.dictionary.data.LexicalItem;
import rx.dictionary.jpa.entity.Explanation;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static rx.dictionary.jpa.repository.ITUtil.generateLexicalItem;

public class ExplanationRepositoryReadIT extends AbstractDatabaseConfiguration {

    @BeforeAll
    public static void insert() {
        final Long generatedId = generateLexicalItem(preparedStatementExecutor, Locale.ENGLISH, "test");
        preparedStatementExecutor.execute("insert into explanation (id, lexical_item_id, language, partOfSpeech, definition) value (NEXT VALUE FOR explanation_id_seq,?,?,?,?)", statement -> {
            statement.setLong(1, generatedId); // Set value for column1
            statement.setString(2, Locale.SIMPLIFIED_CHINESE.toString());
            statement.setString(3, "N");
            statement.setString(4, "测试 1");
            statement.addBatch();
            statement.setLong(1, generatedId); // Set value for column1
            statement.setString(2, Locale.SIMPLIFIED_CHINESE.toString());
            statement.setString(3, "N");
            statement.setString(4, "测试 2");
            statement.addBatch();
            statement.setLong(1, generatedId); // Set value for column1
            statement.setString(2, Locale.ENGLISH.toString());
            statement.setString(3, "N");
            statement.setString(4, "test in English");
            statement.addBatch();
            statement.executeBatch();
        });
        final Long generatedId2 = generateLexicalItem(preparedStatementExecutor, Locale.ENGLISH, "other");
        preparedStatementExecutor.execute("insert into explanation (id, lexical_item_id, language, partOfSpeech, definition) value (NEXT VALUE FOR explanation_id_seq,?,?,?,?)", statement -> {
            statement.setLong(1, generatedId2); // Set value for column1
            statement.setString(2, Locale.SIMPLIFIED_CHINESE.toString());
            statement.setString(3, "N");
            statement.setString(4, "其他");
            statement.execute();
        });


    }
    @Test
    public void findLike_base() {
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            ExplanationRepository out = new ExplanationRepository(entityManager);
            LexicalItem keyword = new LexicalItem() {
                @Override
                public Locale getLanguage() {
                    return Locale.ENGLISH;
                }

                @Override
                public String getValue() {
                    return "test";
                }
            };
            List<Explanation> result = out.findLike(keyword, Locale.SIMPLIFIED_CHINESE);
            assertEquals(2, result.size());
            assertAll("",
                    () -> {
                        Explanation first = result.get(0);
                        assertEquals("测试 1", first.getDefinition());
                    });
        });
    }
}
