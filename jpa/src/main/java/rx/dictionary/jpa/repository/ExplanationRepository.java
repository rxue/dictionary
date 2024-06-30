package rx.dictionary.jpa.repository;

import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.List;
import java.util.Locale;

public interface ExplanationRepository {
    List<Explanation> findLike(Keyword keyword, Locale explanationLanguage);

    void create(List<Explanation> explanation1);
}
