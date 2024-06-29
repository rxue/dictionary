package rx.dictionary.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.LexicalItemRepositoryImpl;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LexicalItemRepositoryImplDeleteIT extends AbstractDatabaseConfiguration {
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
            statement.executeUpdate();
        });
    }
    private Optional<LexicalItem> getLexicalItem() {
        final LexicalItem existingSingleItem = executeTransactionWithReturnValue("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LexicalItem lexicalItem = new LexicalItem(resultSet.getLong("id"));
                    lexicalItem.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    lexicalItem.setValue(resultSet.getString("value"));
                    return lexicalItem;
                } else {
                    return null;
                }
            }
        });
        if (existingSingleItem != null) {
            //getExplanations(existingSingleItem.getId()).forEach(existingSingleItem::addExplanation);
            return Optional.of(existingSingleItem);
        }
        return Optional.empty();
    }
    private static Set<Explanation> getExplanations(long lexicalItemId) {
        Set<Explanation> result = new HashSet<>();
        executeTransactionWithReturnValue("select * from explanation where lexical_item_id = ?", preparedStatement -> {
            preparedStatement.setLong(1, lexicalItemId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Explanation explanation = new Explanation(resultSet.getLong("id"));
                    explanation.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    explanation.setExplanation(resultSet.getString("explanation"));
                    result.add(explanation);
                }
                return result;
            }
        });
        return result;
    }

    @Test
    public void deleteById() {
        final LexicalItem existingSingleItem = getLexicalItem().get();
        final Long lexicalItemId = existingSingleItem.getId();
        //ACT
        executeTransaction(entityManager -> {
            LexicalItemRepositoryImpl out = new LexicalItemRepositoryImpl(entityManager);
            out.deleteById(lexicalItemId);
        });
        //ASSERT
        assertTrue(getLexicalItem().isEmpty());
        assertTrue(getExplanations(existingSingleItem.getId()).isEmpty());
    }
}
