package rx.dictionary.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import rx.dictionary.jpa.entity.DictionaryEntry;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.repository.DictionaryEntryRepository;
import rx.transaction.jdbc.PreparedStatementExecutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.Consumer;

class ITUtil {
    private ITUtil() {
    }
    static Long generateLexicalItem(PreparedStatementExecutor preparedStatementExecutor, Locale language, String value) {
        return preparedStatementExecutor.executeAndReturn("insert into lexical_item (language,value) value (?,?)", statement -> {
            statement.setString(1, language.toString()); // Set value for column1
            statement.setString(2, value); // Set value for column2
            statement.executeUpdate(); //Execute the insert statement
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1); // the ID is a long (modify based on data type)
                } else {
                    throw new IllegalStateException("ID generation failure");
                }
            }
        });
    }

    static String localeStringToTag(String localeString) {
        return localeString.replace("_", "-");
    }

    static DictionaryEntry getFirstLexicalItem(PreparedStatementExecutor preparedStatementExecutor) {
        final DictionaryEntry existingSingleItem = preparedStatementExecutor.executeAndReturn("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    DictionaryEntry lexicalItem = new DictionaryEntry(resultSet.getLong("id"));
                    lexicalItem.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    lexicalItem.setValue(resultSet.getString("value"));
                    return lexicalItem;
                }
                throw new IllegalArgumentException(preparedStatement + " does not return any result");
            }
        });
        final Set<Explanation> explanations = preparedStatementExecutor.executeAndReturn("select * from explanation where lexical_item_id = ?", preparedStatement -> {
            preparedStatement.setLong(1, existingSingleItem.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Explanation> result = new HashSet<>();
                while (resultSet.next()) {
                    Explanation explanation = new Explanation(resultSet.getLong("id"));
                    String localeTag = localeStringToTag(resultSet.getString("language"));
                    explanation.setLanguage(Locale.forLanguageTag(localeTag));
                    explanation.setExplanation(resultSet.getString("explanation"));
                    result.add(explanation);
                }
                return result;
            }
        });
        explanations.forEach(existingSingleItem::addExplanation);
        return existingSingleItem;
    }
}