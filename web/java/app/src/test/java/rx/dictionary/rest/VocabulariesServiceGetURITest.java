package rx.dictionary.rest;

import org.junit.jupiter.api.Test;
import rx.dictionary.rest.dto.ExplanationsDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VocabulariesServiceGetURITest {
    @Test
    public void getURI_withSpace() {
        LexicalItemDTO lexicalItemDTO = new LexicalItemDTO();
        lexicalItemDTO.setLanguage("en");
        lexicalItemDTO.setValue("value x");
        ExplanationsDTO explanationsDTO = new ExplanationsDTO();
        explanationsDTO.setLexicalItem(lexicalItemDTO);
        explanationsDTO.setExplanationLanguage("en");
        assertEquals(URI.create("vocabularies/en;explanation_language=en/value%20x"), VocabulariesResource.getURI(explanationsDTO));
    }
}
