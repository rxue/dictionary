package rx.dictionary.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import rx.dictionary.jpa.entity.DictionaryEntry;
import rx.dictionary.jpa.repository.DictionaryEntryRepository;

import java.util.function.Consumer;

public class ITUtil {
    private ITUtil() {
    }

    static DictionaryEntry getSingleLexicalItem(EntityManager em) {
        return em.createQuery("SELECT l FROM LexicalItem l", DictionaryEntry.class)
                .getSingleResult();
    }

    static String localeStringToTag(String localeString) {
        return localeString.replace("_", "-");
    }

}