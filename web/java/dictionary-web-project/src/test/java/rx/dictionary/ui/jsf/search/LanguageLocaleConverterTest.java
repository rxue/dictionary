package rx.dictionary.ui.jsf.search;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageLocaleConverterTest {
    @Test
    public void getAsString_SimplifiedChinese() {
        LanguageLocaleConverter tested = new LanguageLocaleConverter();
        String actualResult = tested.getAsString(null, null, Locale.SIMPLIFIED_CHINESE);
        assertEquals("zh-CN", actualResult);
    }
}
