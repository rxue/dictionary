package rx.dictionary.rest;

import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.rest.dto.ExplanationDTO;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class VocabulariesServiceTest {
    private VocabulariesService out;
    //@Test
    public void combine_eachExistingExplanation() {
        LexicalItem lexicalItem = new LexicalItem();
        lexicalItem.setLanguage(Locale.FRENCH);
        lexicalItem.setValue("bonjour");
        Explanation originalExplanation = mockExplanation(lexicalItem, PartOfSpeech.N, "hello world");
        ExplanationDTO newExplanation = new ExplanationDTO();
        newExplanation.setPartOfSpeech("INTERJ");
        newExplanation.setExplanation("hello");
        //WHEN
        List<Explanation> result = out.combine(List.of(originalExplanation), List.of(newExplanation));
        //THEN
        Explanation expected = mockExplanation(lexicalItem, PartOfSpeech.INTERJ, "hello");
        assertEquals(mockExplanation(lexicalItem, PartOfSpeech.INTERJ, "hello"), result.get(0));
    }

    private Explanation mockExplanation(LexicalItem lexicalItem, PartOfSpeech partOfSpeech, String explanation) {
        Explanation originalExplanation = new Explanation();
        //originalExplanation.setLexicalItem(lexicalItem);
        originalExplanation.setLanguage(Locale.ENGLISH);
        originalExplanation.setPartOfSpeech(partOfSpeech);
        originalExplanation.setExplanation(explanation);
        return originalExplanation;
    }

}
