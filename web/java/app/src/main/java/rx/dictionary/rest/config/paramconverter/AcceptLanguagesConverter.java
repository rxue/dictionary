package rx.dictionary.rest.config.paramconverter;

import jakarta.ws.rs.ext.ParamConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class AcceptLanguagesConverter implements ParamConverter<List<Locale>> {
    @Override
    public List<Locale> fromString(String s) {
        //example input: zh-CN,en;q=0.7,zh-TW;q=0.3
        return Arrays.stream(s.split(","))
                .map(localeStr -> Locale.forLanguageTag(localeStr.split(";")[0]))
                .collect(toList());
    }

    @Override
    public String toString(List<Locale> locales) {
        return null;
    }
}
