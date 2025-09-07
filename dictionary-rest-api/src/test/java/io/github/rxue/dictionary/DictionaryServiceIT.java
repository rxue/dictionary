package io.github.rxue.dictionary;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;

import java.util.Locale;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@QuarkusTest
public class DictionaryServiceIT {
    @Inject
    private EntityManager entityManager;
    private static DictionaryService dictionaryService;

    @BeforeEach
    public void init() {
        if (dictionaryService == null)
            dictionaryService = new DictionaryService(entityManager);
    }

    @Test
    public void getExplanationsByLanguage_when_there_is_no_match() {
        assertThat(dictionaryService.getExplanationsByLanguage(Locale.US, "curate", Locale.SIMPLIFIED_CHINESE),
                equalTo(Optional.empty()));
    }
}
