package rx.dictionary.rest.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.PartOfSpeech;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplanationsByLanguageDTOBuilderTest {
    private ExplanationsByLanguageDTO.Builder out;
    @BeforeEach
    public void init() {
        out = new ExplanationsByLanguageDTO.Builder(Locale.ENGLISH);
    }
     @Test
    public void addExplanation_addOne() {
        final String explanationValue = "first expalantion";
        out.addExplanation(initExplanation(PartOfSpeech.N, explanationValue));
        Map<String, Set<String>> expectedResult = new HashMap<>();
        expectedResult.put(PartOfSpeech.N.toString(), Set.of(explanationValue));
        assertEquals(expectedResult, out.build().getExplanationsByPartOfSpeech());
    }
    @Test
    public void addExplanation_addTwoWithSamePartOfSpeech() {
        final String firstExplanationValue = "first expalantion";
        final PartOfSpeech noun = PartOfSpeech.N;
        out.addExplanation(initExplanation(noun, firstExplanationValue));
        final String secondExplanationValue = "second expalantion";
        out.addExplanation(initExplanation(noun, secondExplanationValue));
        Map<String, Set<String>> expectedResult = new HashMap<>();
        expectedResult.put(noun.toString(), Set.of(firstExplanationValue, secondExplanationValue));
        //ACT
        Map<String, Set<String>> actualResult = out.build().getExplanationsByPartOfSpeech();
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void addExplanation_addThreeWithDifferentPartOfSpeechs() {
        final String firstExplanationValue = "first expalantion";
        final PartOfSpeech noun = PartOfSpeech.N;
        final String secondExplanationValue = "second expalantion";
        final String thirdExplanationValue = "third expalantion";
        final PartOfSpeech adj = PartOfSpeech.ADJ;
        Map<String, Set<String>> expectedResult = new HashMap<>();
        expectedResult.put(noun.toString(), Set.of(firstExplanationValue, secondExplanationValue));
        expectedResult.put(adj.toString(), Set.of(thirdExplanationValue));
        out.addExplanation(initExplanation(noun, firstExplanationValue));
        out.addExplanation(initExplanation(noun, secondExplanationValue));
        out.addExplanation(initExplanation(adj, thirdExplanationValue));
        //ACT
        Map<String, Set<String>> actualResult = out.build().getExplanationsByPartOfSpeech();
        //ASSERT
        assertEquals(expectedResult, actualResult);
    }
    private static Explanation initExplanation(PartOfSpeech partOfSpeech, String explanationValue) {
        Explanation explanation = new Explanation();
        explanation.setPartOfSpeech(partOfSpeech);
        explanation.setExplanation(explanationValue);
        return explanation;
    }
}
