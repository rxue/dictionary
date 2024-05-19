package rx.dictionary.rest;

import org.junit.jupiter.api.Test;
import rx.dictionary.rest.dto.LexicalItemWithLegacyExplanationsDTO;
import rx.dictionary.rest.dto.LegacyLexicalItemDTO;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VocabulariesServiceGetURITest {
    @Test
    public void getURI_withSpace() {
        LegacyLexicalItemDTO legacyLexicalItemDTO = new LegacyLexicalItemDTO();
        legacyLexicalItemDTO.setLanguage("en");
        legacyLexicalItemDTO.setValue("value x");
        LexicalItemWithLegacyExplanationsDTO lexicalItemWithExplanationsDTO = new LexicalItemWithLegacyExplanationsDTO();
        lexicalItemWithExplanationsDTO.setLexicalItem(legacyLexicalItemDTO);
        lexicalItemWithExplanationsDTO.setExplanationLanguage("en");
        assertEquals(URI.create("vocabularies/en;explanation_language=en/value%20x"), VocabulariesResource.getURI(lexicalItemWithExplanationsDTO));
    }
}
