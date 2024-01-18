package rx.dictionary.jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.LexicalItemRepository;
import rx.dictionary.jpa.vo.Keyword;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LexicalItemRepositoryFindIT extends AbstractDatabaseConfiguration {
    private static LexicalItemRepository repository;

    @Test
    public void testFind() {
        LexicalItemRepositoryAddIT.insert();
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            LexicalItemRepository repository = new LexicalItemRepository(em);
            List<LexicalItem> lexicalItems = repository.findByKeywordStartWith(new Keyword("tak", Locale.forLanguageTag("en")), Locale.forLanguageTag("en"));
            assertAll("",
                    () -> assertFalse(lexicalItems.isEmpty()),
                    () -> {
                        Set<Explanation> explanations = lexicalItems.get(0).getExplanations();
                        assertFalse(explanations.isEmpty());
                        explanations.forEach(e -> assertNotNull(e.getId()));
                    });
        }
    }
}
