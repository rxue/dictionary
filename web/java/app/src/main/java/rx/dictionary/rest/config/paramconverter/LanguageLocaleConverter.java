package rx.dictionary.rest.config.paramconverter;

import jakarta.ws.rs.ext.ParamConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class LanguageLocaleConverter implements ParamConverter<Locale> {
    @Override
    public Locale fromString(String s) {
        System.out.println("convert to locale");
        return Locale.forLanguageTag(s);
    }

    @Override
    public String toString(Locale locales) {
        return null;
    }
}
