package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import rx.dictionary.dto.LexicalItemDTO;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class LexicalItemRepository {
    @Inject
    private EntityManager em;
    public void create(LexicalItem lexicalItem) {
        em.persist(lexicalItem);
        System.out.println("right after persist and before commit the entity LexicalItem id is " + lexicalItem.getId());
    }
    public Optional<LexicalItem> find(final LexicalItemDTO lexicalItemDTO) {
        List<LexicalItem> result = em.createQuery("SELECT l FROM LexicalItem l WHERE l.language = :language AND l.value = :value",
                LexicalItem.class)
                .setParameter("language", Locale.forLanguageTag(lexicalItemDTO.getLanguage()))
                .setParameter("value", lexicalItemDTO.getValue())
                .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
}
