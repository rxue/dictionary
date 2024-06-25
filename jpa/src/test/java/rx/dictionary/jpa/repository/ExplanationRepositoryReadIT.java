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

public class ExplanationRepositoryReadIT extends AbstractDatabaseConfiguration {
    @BeforeEach
    public void addLexicalItem() {
        final Long generatedId = executeTransactionWithReturnValue("insert into lexical_item (language,value) value (?,?)", statement -> {
            statement.setString(1, Locale.ENGLISH.toLanguageTag()); // Set value for column1
            statement.setString(2, "test"); // Set value for column2
            statement.executeUpdate(); //Execute the insert statement
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1); // Assuming the ID is a long (modify based on data type)
                } else {
                    throw new IllegalStateException("ID generation failure");
                }
            }
        });
        executeTransaction("insert into explanation (id, lexical_item_id, language, partOfSpeech, explanation) value (NEXT VALUE FOR explanation_id_seq,?,?,?,?)", statement -> {
            statement.setLong(1, generatedId); // Set value for column1
            statement.setString(2, Locale.SIMPLIFIED_CHINESE.toLanguageTag());
            statement.setString(3, "N");
            statement.setString(4, "测试");
            statement.addBatch();
            statement.setLong(1, generatedId); // Set value for column1
            statement.setString(2, Locale.ENGLISH.toLanguageTag());
            statement.setString(3, "N");
            statement.setString(4, "test");
            statement.addBatch();
            statement.executeBatch();
        });
    }

    @Test
    public void find_base() {
        //ACT
        executeTransaction(entityManager -> {
            ExplanationRepository out = new ExplanationRepository(entityManager);
            List<Explanation> result = out.find(new Keyword("test", Locale.ENGLISH));
            assertFalse(result.isEmpty());
        });
    }
}
