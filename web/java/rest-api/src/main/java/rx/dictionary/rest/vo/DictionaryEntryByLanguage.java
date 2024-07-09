package rx.dictionary.rest.vo;

import rx.dictionary.vo.LexicalItem;

import java.util.Locale;

public class DictionaryEntryByLanguage implements LexicalItem {
    private final Locale language;
    private final String value;
    public DictionaryEntryByLanguage(Locale language, String value) {
        this.language = language;
        this.value = value;
    }

    @Override
    public Locale getLanguage() {
        return null;
    }

    @Override
    public String getValue() {
        return "";
    }
}
