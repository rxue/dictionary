package rx.dictionary.rest;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplanationLanguageResolverTest {
    @Test
    public void acceptLanguageIsNull() {
        Locale actualResult = new ExplanationLanguageResolver.Builder()
                .setSearchKeywordLanguage(Locale.ENGLISH)
                .setAcceptLanguages(null)
                .setExplanationLanguage(Locale.ENGLISH)
                .build()
                .resolve();
        assertEquals(Locale.ENGLISH, actualResult);
    }

    @Test
    public void firstAcceptLanguageIsUSEnglish() {
        Locale actualResult = new ExplanationLanguageResolver.Builder()
                .setSearchKeywordLanguage(Locale.ENGLISH)
                .setAcceptLanguages(List.of(Locale.US))
                .setExplanationLanguage(Locale.ENGLISH)
                .build()
                .resolve();
        assertEquals(Locale.ENGLISH, actualResult);
    }
}
