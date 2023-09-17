package rx.dictionary;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchKeywordTest {
    @Test
    public void nullLanguageLocale() {
        assertThrows(NullPointerException.class, () -> new SearchKeyword("test", null));
    }
}
