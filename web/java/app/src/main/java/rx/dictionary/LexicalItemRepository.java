package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.vo.LexicalItemVO;

import java.util.List;
import java.util.Optional;

public class LexicalItemRepository {
    @Inject
    private EntityManager em;
    public void create(LexicalItem lexicalItem) {
        em.persist(lexicalItem);
        System.out.println("right after persist and before commit the entity LexicalItem id is " + lexicalItem.getId());
    }
    public LexicalItem create(LexicalItemVO lexicalItemVO) {
        LexicalItem newLexicalItem = new LexicalItem();
        newLexicalItem.setLanguage(lexicalItemVO.getLanguage());
        newLexicalItem.setValue(lexicalItemVO.getValue());
        em.persist(newLexicalItem);
        System.out.println("right after persist and before commit the entity LexicalItem id is " + newLexicalItem.getId());
        return newLexicalItem;
    }

    public Optional<LexicalItem> find(LexicalItemVO lexicalItem) {
        List<LexicalItem> result= em.createQuery("select l from LexicalItem l where l.language = :language and l.value = :value", LexicalItem.class)
            .setParameter("language", lexicalItem.getLanguage())
            .setParameter("value", lexicalItem.getValue())
            .getResultList();
        System.out.println("result is empty:::::::::::::::::" + result.isEmpty());
        if (result.isEmpty()) return Optional.empty();
        else if (result.size() > 1) throw new IllegalStateException("Database has problem, (language, value) pair has to be unique");
        return Optional.of(result.get(0));
    }
}
