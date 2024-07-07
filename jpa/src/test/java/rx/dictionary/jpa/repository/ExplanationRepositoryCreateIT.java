package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.PartOfSpeech;

import java.sql.ResultSet;
import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class ExplanationRepositoryCreateIT extends AbstractDatabaseConfiguration {
    @AfterEach
    public void truncateTables() {
        ITUtil.truncateTables(preparedStatementExecutor);
    }

    @Test
    public void testCreate_addNewLexicalItemWithOneExplanation() {
        //ACT
        userTransactionExecutor.execute(entityManager -> {
                    LexicalItem l = new LexicalItem();
                    l.setLanguage(Locale.ENGLISH);
                    l.setValue("take");
                    Explanation explanation = new Explanation(null, l);
                    explanation.setLanguage(Locale.SIMPLIFIED_CHINESE);
                    explanation.setDefinition("行动");
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.cascadeAdd(List.of(explanation));
        });
        //ASSERT
        final Explanation explanation = ITUtil.getAllExplanations(preparedStatementExecutor, "take")
                .stream().findAny().get();
        final LexicalItem createdLexicalItem = explanation.getDictionaryEntry();
        assertEquals("take", createdLexicalItem.getValue());
        assertAll("assert explanation",
                () -> assertEquals(Locale.SIMPLIFIED_CHINESE, explanation.getLanguage()),
                () -> assertEquals("行动", explanation.getDefinition()));
    }
    @Test
    public void testCreate_addNewLexicalItemWith2Explanations() {
        //PREPARE
        Supplier<Collection<Explanation>> prepareExplanations = () -> {
            LexicalItem l = new LexicalItem();
            l.setLanguage(Locale.ENGLISH);
            l.setValue("take");
            Explanation explanation = new Explanation(null, l);
            explanation.setLanguage(Locale.SIMPLIFIED_CHINESE);
            explanation.setDefinition("行动");
            Explanation explanation2 = new Explanation(null, l);
            explanation2.setLanguage(Locale.SIMPLIFIED_CHINESE);
            explanation2.setPartOfSpeech(PartOfSpeech.N);
            explanation2.setDefinition("见解");
            return List.of(explanation, explanation2);
        };
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.cascadeAdd(prepareExplanations.get());
        });
        //ASSERT
        final List<LexicalItem> existingItems = preparedStatementExecutor.executeAndReturn("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<LexicalItem> createdItems = new ArrayList<>();
                while (resultSet.next()) {
                    LexicalItem lexicalItem = new LexicalItem(resultSet.getLong("id"));
                    lexicalItem.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    lexicalItem.setValue(resultSet.getString("value"));
                    createdItems.add(lexicalItem);
                }
                return createdItems;
            }
        });
        Set<Explanation> addedExplanations = ITUtil.getAllExplanations(preparedStatementExecutor, "take");
        assertAll("",
                () -> assertEquals(1, existingItems.size()),
                () -> assertEquals(2, addedExplanations.size()),
                () -> {
                    String addedExplanationValue = addedExplanations.stream().findAny()
                            .get().
                            getDefinition();
                    assertTrue("行动".equals(addedExplanationValue) || "见解".equals(addedExplanationValue));
                });

    }

}
