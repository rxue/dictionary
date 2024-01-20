package rx.dictionary.jpa;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.LexicalItemRepository;
import rx.dictionary.jpa.vo.Keyword;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static rx.dictionary.jpa.LexicalItemRepositoryCreateIT.executeTransaction;

public class LexicalItemRepositoryReadIT extends AbstractDatabaseConfiguration {
    @BeforeAll
    public static void init() {
        AbstractDatabaseConfiguration.init();
        executeTransaction(repo -> {
                    LexicalItem l = new LexicalItem();
                    l.setLanguage(Locale.forLanguageTag("en"));
                    l.setValue("take");
                    Explanation explanation = new Explanation();
                    explanation.setLanguage(Locale.forLanguageTag("en"));
                    explanation.setExplanation("action of taking");
                    l.addExplanation(explanation);
                    repo.add(l);
                }
        );
    }
    @Test
    public void testFind() {
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
