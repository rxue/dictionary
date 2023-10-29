package rx.dictionary.rest;

import org.junit.jupiter.api.Test;
import rx.dictionary.rest.dto.LexicalItemWithExplanationsDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VocabulariesServiceGetURITest {
    @Test
    public void getURI_withSpace() {
        LexicalItemDTO lexicalItemDTO = new LexicalItemDTO();
        lexicalItemDTO.setLanguage("en");
        lexicalItemDTO.setValue("value x");
        LexicalItemWithExplanationsDTO lexicalItemWithExplanationsDTO = new LexicalItemWithExplanationsDTO();
        lexicalItemWithExplanationsDTO.setLexicalItem(lexicalItemDTO);
        lexicalItemWithExplanationsDTO.setExplanationLanguage("en");
        assertEquals(URI.create("vocabularies/en;explanation_language=en/value%20x"), VocabulariesResource.getURI(lexicalItemWithExplanationsDTO));
    }
}
