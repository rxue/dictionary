package rx.dictionary.jpa.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.ExplanationEntity;
import rx.dictionary.jpa.entity.LexicalItemEntity;
import rx.dictionary.jpa.entity.PartOfSpeech;

import java.sql.ResultSet;
import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rx.dictionary.jpa.repository.ITUtil.*;

public class ExplanationEntityRepositoryCreateIT extends AbstractDatabaseConfiguration {
    @AfterEach
    public void truncateTables() {
        ITUtil.truncateTables(preparedStatementExecutor);
    }

    @Test
    public void testCreate_addNewLexicalItemWithOneExplanation() {
        //ACT
        userTransactionExecutor.execute(entityManager -> {
                    LexicalItemEntity l = new LexicalItemEntity();
                    l.setLanguage(Locale.ENGLISH);
                    l.setValue("take");
                    ExplanationEntity explanationEntity = new ExplanationEntity(null, l);
                    explanationEntity.setLanguage(Locale.SIMPLIFIED_CHINESE);
                    explanationEntity.setPartOfSpeech(PartOfSpeech.VT);
                    explanationEntity.setDefinition("行动");
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.cascadeAdd(List.of(explanationEntity));
        });
        //ASSERT
        final ExplanationEntity explanationEntity = ITUtil.getAllExplanations(preparedStatementExecutor, "take")
                .stream().findAny().get();
        LexicalItemVO expectedLexicalItem = new LexicalItemVO(Locale.ENGLISH.toString(), "take");
        assertEquals(new ExplanationVO(expectedLexicalItem, Locale.SIMPLIFIED_CHINESE.toString(), PartOfSpeech.VT, "行动"), toExplanationVO(explanationEntity));
    }
    @Test
    public void testCreate_addNewLexicalItemWith2Explanations() {
        //PREPARE
        Supplier<Collection<ExplanationEntity>> prepareExplanations = () -> {
            LexicalItemEntity l = new LexicalItemEntity();
            l.setLanguage(Locale.ENGLISH);
            l.setValue("take");
            ExplanationEntity explanationEntity = new ExplanationEntity(null, l);
            explanationEntity.setLanguage(Locale.SIMPLIFIED_CHINESE);
            explanationEntity.setPartOfSpeech(PartOfSpeech.VT);
            explanationEntity.setDefinition("test explanation 1");
            ExplanationEntity explanationEntity2 = new ExplanationEntity(null, l);
            explanationEntity2.setLanguage(Locale.SIMPLIFIED_CHINESE);
            explanationEntity2.setPartOfSpeech(PartOfSpeech.N);
            explanationEntity2.setDefinition("test explanation 2");
            return List.of(explanationEntity, explanationEntity2);
        };
        //ACT
        userTransactionExecutor.execute(entityManager -> {
            ExplanationRepository out = new ExplanationRepository(entityManager);
            out.cascadeAdd(prepareExplanations.get());
        });

        //ASSERT
        final List<LexicalItemEntity> existingItems = preparedStatementExecutor.executeAndReturn("select * from lexical_item", preparedStatement -> {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<LexicalItemEntity> createdItems = new ArrayList<>();
                while (resultSet.next()) {
                    LexicalItemEntity lexicalItem = new LexicalItemEntity(resultSet.getLong("id"));
                    lexicalItem.setLanguage(Locale.forLanguageTag(resultSet.getString("language")));
                    lexicalItem.setValue(resultSet.getString("value"));
                    createdItems.add(lexicalItem);
                }
                return createdItems;
            }
        });
        List<ExplanationEntity> addedExplanationEntities = ITUtil.getAllExplanations(preparedStatementExecutor, "take");
        LexicalItemVO expectedLexicalItem = new LexicalItemVO(Locale.ENGLISH.toString(), "take");
        ExplanationVO expectedExplanation = new ExplanationVO(expectedLexicalItem, Locale.SIMPLIFIED_CHINESE.toString(), PartOfSpeech.VT, "test explanation 1");

        ExplanationVO expectedExplanation2 = new ExplanationVO(expectedLexicalItem, Locale.SIMPLIFIED_CHINESE.toString(), PartOfSpeech.N, "test explanation 2");
        List<ExplanationVO> expectedExplanations = List.of(expectedExplanation, expectedExplanation2);
        int i = 0;
        for (ExplanationVO explanationEntity : expectedExplanations) {
                assertEquals(explanationEntity, toExplanationVO(addedExplanationEntities.get(i++)));
        }

    }

}
