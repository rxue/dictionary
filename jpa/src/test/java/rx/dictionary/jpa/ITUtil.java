package rx.dictionary.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.jpa.repository.LexicalItemRepository;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class ITUtil {
    private ITUtil() {
    }
    public static LexicalItem newLexicalItem(Locale language, String value) {
        LexicalItem lexicalItem = new LexicalItem();
        lexicalItem.setLanguage(language);
        lexicalItem.setValue(value);
        return lexicalItem;
    }
    public static Explanation newExplanation(Locale language, PartOfSpeech partOfSpeech, String explanation) {
        Explanation newExplanation = new Explanation();
        newExplanation.setLanguage(language);
        newExplanation.setPartOfSpeech(partOfSpeech);
        newExplanation.setExplanation(explanation);
        return newExplanation;
    }

    static LexicalItem getSingleLexicalItem(EntityManager em) {
        return em.createQuery("SELECT l FROM LexicalItem l", LexicalItem.class)
                .getSingleResult();
    }
    static void executeTransaction(EntityManagerFactory entityManagerFactory, Consumer<LexicalItemRepository> operations)  {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            operations.accept(new LexicalItemRepository(em));
            transaction.commit();
        }
        System.out.println("transaction committed");
    }
}