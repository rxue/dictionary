package rx.dictionary.rest.paramconverter;

import jakarta.ws.rs.ext.ParamConverter;
import org.junit.jupiter.api.Test;
import rx.dictionary.rest.config.paramconverter.AcceptLanguagesConverter;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class AcceptLanguagesConverterTest {
    @Test
    public void base() {
        ParamConverter<List<Locale>> tested = new AcceptLanguagesConverter();
        List<Locale> actualResult = tested.fromString("zh-CN,en;q=0.7,zh-TW;q=0.3");
        assertIterableEquals(List.of(Locale.SIMPLIFIED_CHINESE, Locale.ENGLISH, Locale.TRADITIONAL_CHINESE), actualResult);
    }

    @Test
    public void baseWithUSEnglish() {
        ParamConverter<List<Locale>> tested = new AcceptLanguagesConverter();
        List<Locale> actualResult = tested.fromString("zh-CN,en;q=0.7,zh-TW;q=0.3");
        assertIterableEquals(List.of(Locale.SIMPLIFIED_CHINESE, Locale.ENGLISH, Locale.TRADITIONAL_CHINESE), actualResult);
    }
}
