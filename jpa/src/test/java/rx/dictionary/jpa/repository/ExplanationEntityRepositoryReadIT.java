package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.ExplanationEntity;
import rx.dictionary.jpa.entity.LexicalItemEntity;
import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.vo.Keyword;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static rx.dictionary.jpa.repository.ITUtil.*;

public class ExplanationEntityRepositoryReadIT extends AbstractDatabaseConfiguration {

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
            Keyword keyword = new Keyword() {

                @Override
                public String getLanguage() {
                    return Locale.ENGLISH.toString();
                }

                @Override
                public String getValue() {
                    return "test";
                }
            };
            List<ExplanationEntity> result = out.findLike(keyword, Locale.SIMPLIFIED_CHINESE);
            assertEquals(2, result.size());
            final ExplanationEntity firstExplanationEntity = result.get(0);
            LexicalItemVO expectedLexicalItem = new LexicalItemVO(Locale.ENGLISH.toString(), "test");
            assertEquals(new ExplanationVO(expectedLexicalItem,
                    Locale.SIMPLIFIED_CHINESE.toString(), PartOfSpeech.N, "测试 1"), toExplanationVO(firstExplanationEntity));
        });
    }

}
