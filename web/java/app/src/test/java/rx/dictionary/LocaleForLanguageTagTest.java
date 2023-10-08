package rx.dictionary;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocaleForLanguageTagTest {
    @Test
    public void simplifiedChinese() {
        assertEquals(Locale.SIMPLIFIED_CHINESE, Locale.forLanguageTag("zh-CN"));
    }

    @Test
    public void finnish() {
        assertEquals(new Locale("fi"), Locale.forLanguageTag("fi"));
    }
}
