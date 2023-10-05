package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.vo.LexicalItemVO;

import java.util.Optional;

public class LexicalItemRepository {
    @Inject
    private EntityManager em;
    public void create(LexicalItem lexicalItem) {
        em.persist(lexicalItem);
        System.out.println("right after persist and before commit the entity LexicalItem id is " + lexicalItem.getId());
    }

    public Optional<LexicalItem> find(LexicalItemVO lexicalItem) {
        LexicalItem result= em.createQuery("select l from LexicalItem l where l.language = :language and l.value = :value", LexicalItem.class)
            .setParameter("language", lexicalItem.getLanguage())
            .setParameter("value", lexicalItem.getValue())
            .getSingleResult();
        return Optional.ofNullable(result);
    }
}
