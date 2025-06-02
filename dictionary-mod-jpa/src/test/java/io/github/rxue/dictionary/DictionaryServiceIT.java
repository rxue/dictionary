package io.github.rxue.dictionary;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    public void queryFromDatabase() {
        assertNotNull(dictionaryService.getExplanationsByLanguage(Locale.US, "curate", Locale.SIMPLIFIED_CHINESE));
    }
    @Test
    public void queryFromDatabase2() {
        assertNotNull(dictionaryService.getExplanationsByLanguage(Locale.US, "curate", Locale.SIMPLIFIED_CHINESE));
    }
}
