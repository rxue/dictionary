package rx.dictionary.rest;

import java.util.List;
import java.util.Locale;

class ExplanationLanguageResolver {
    private final Locale explanationLanguage;
    private final List<Locale> acceptLanguages;
    private final Locale searchKeywordLanguage;
    private ExplanationLanguageResolver(Builder builder) {
        this.explanationLanguage = builder.explanationLanguage;
        this.acceptLanguages = builder.acceptLanguages;
        this.searchKeywordLanguage = builder.searchKeywordLanguage;
    }
    public Locale resolve() {
        if (explanationLanguage != null) return explanationLanguage;
        if (acceptLanguages == null)
            return searchKeywordLanguage;
        else {
            Locale firstLocale = acceptLanguages.get(0);
            if (firstLocale == Locale.US) return Locale.ENGLISH;
            return firstLocale;
        }
    }
    static class Builder {
        private Locale explanationLanguage;
        private List<Locale> acceptLanguages;
        private Locale searchKeywordLanguage;

        public Builder setExplanationLanguage(Locale explanationLanguage) {
            this.explanationLanguage = explanationLanguage;
            return this;
        }

        public Builder setAcceptLanguages(List<Locale> acceptLanguages) {
            this.acceptLanguages = acceptLanguages;
            return this;
        }

        public Builder setSearchKeywordLanguage(Locale searchKeywordLanguage) {
            this.searchKeywordLanguage = searchKeywordLanguage;
            return this;
        }
        public ExplanationLanguageResolver build() {
            return new ExplanationLanguageResolver(this);
        }

    }
}
