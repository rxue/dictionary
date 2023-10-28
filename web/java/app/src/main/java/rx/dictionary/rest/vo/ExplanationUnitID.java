package rx.dictionary.rest.vo;

import rx.dictionary.vo.LexicalItemVO;

import java.util.Locale;

public record ExplanationUnitID(Locale language, String value, Locale explanationLanguage) {
    public LexicalItemVO lexicalItem() {
        return new LexicalItemVO(language, value);
    }
}
